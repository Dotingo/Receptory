package dev.dotingo.receptory.presentation.screens.authorization

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.nulabinc.zxcvbn.Zxcvbn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.R
import dev.dotingo.receptory.constants.FirebaseConstants
import dev.dotingo.receptory.data.database.entities.CategoryEntity
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.data.datastore.DataStoreManager
import dev.dotingo.receptory.di.ReceptoryApp
import dev.dotingo.receptory.domain.repository.CategoryRepository
import dev.dotingo.receptory.domain.repository.RecipeRepository
import dev.dotingo.receptory.utils.AuthUtils
import dev.dotingo.receptory.utils.getLocalizedContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val application: ReceptoryApp,
    private val dataStoreManager: DataStoreManager,
    private val auth: FirebaseAuth,
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val firebaseFirestore: FirebaseFirestore
) : ViewModel() {
    private val _navEvents = MutableSharedFlow<NavEvent>()
    val navEvents = _navEvents.asSharedFlow()

    sealed class NavEvent {
        data object ToMain : NavEvent()
        data object ToLogin : NavEvent()
    }

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _verificationDialog = MutableStateFlow(false)
    val verificationDialog: StateFlow<Boolean> = _verificationDialog
    fun closeVerificationDialog() {
        _verificationDialog.value = false
    }

    fun onGoogleSignInResult(result: AuthResult) {
        val user = result.user ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                dataStoreManager.setUserLoggedIn(true)
                fetchAndSaveUserData(user.uid)
                _navEvents.emit(NavEvent.ToMain)
            } catch (e: Exception){
                _uiState.update { it.copy(authErrorMessage = e.message ?: "", isLoading = false) }
            }
        }
    }

    private suspend fun fetchAndSaveUserData(userId: String) = withContext(Dispatchers.IO) {
        try {
            val recipesSnapshot = firebaseFirestore
                .collection(FirebaseConstants.RECIPES)
                .whereEqualTo(FirebaseConstants.USER_ID_FIELD, userId)
                .get()
                .await()
            val recipes = recipesSnapshot.toObjects(RecipeEntity::class.java)
            val updatedRecipes = recipes.map { recipe ->
                if (recipe.imageUrl.startsWith("https://")) {
                    val localPath = downloadImageAndSaveLocally(
                        imageUrl = recipe.imageUrl,
                        fileName = "${recipe.recipeId}.jpg"
                    )
                    if (localPath != null) {
                        recipe.copy(imageUrl = localPath)
                    } else {
                        recipe
                    }
                } else {
                    recipe
                }
            }
            recipeRepository.insertAllRecipes(updatedRecipes)
            val categoriesSnapshot = firebaseFirestore
                .collection(FirebaseConstants.CATEGORIES)
                .whereEqualTo(FirebaseConstants.USER_ID_FIELD, userId)
                .get()
                .await()
            val categories = categoriesSnapshot.toObjects(CategoryEntity::class.java)
            categoryRepository.insertAllCategories(categories)

        } catch (e: Exception) {
            Log.e("AuthorizationVM", "fetchAndSaveUserData failed: ${e.localizedMessage}", e)
        }
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        val passwordError = validatePassword(password)
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = passwordError
        )
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            passwordVisibility = !_uiState.value.passwordVisibility
        )
    }

    fun signIn(onSuccessful: () -> Unit) {
        val languageCode = Locale.getDefault().language
        val localizedContext = getLocalizedContext(application, languageCode)
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.value = state.copy(
                authErrorMessage = localizedContext.getString(R.string.error_email_password_empty)
            )
            return
        }

        _uiState.value = state.copy(isLoading = true)

        auth.signInWithEmailAndPassword(state.email, state.password)
            .addOnSuccessListener { authResult ->
                val user = authResult.user
                if (user != null && user.isEmailVerified) {
                    viewModelScope.launch {
                        try {
                            dataStoreManager.setUserLoggedIn(true)
                            fetchAndSaveUserData(user.uid)
                            _uiState.update { it.copy(isLoading = false) }
                            onSuccessful()
                        } catch (e: Exception) {
                            Log.e("AuthorizationVM", "signIn data fetch failed: ${e.localizedMessage}", e)
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    authErrorMessage = localizedContext.getString(R.string.error_data_sync_failed)
                                )
                            }
                        }
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            authErrorMessage = localizedContext.getString(R.string.error_email_not_verified)
                        )
                    }
                }
            }
            .addOnFailureListener { exception ->
                val errorMessage = getFirebaseAuthErrorMessage(exception, localizedContext)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        authErrorMessage = errorMessage
                    )
                }
            }
    }

    private suspend fun downloadImageAndSaveLocally(imageUrl: String, fileName: String): String? =
        withContext(
            Dispatchers.IO
        ) {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                val imageFile = File(application.filesDir, fileName)
                val outputStream = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
                imageFile.absolutePath
            } catch (e: Exception) {
                Log.d("downloadImageAndSaveLocally", "${e.localizedMessage}")
                e.printStackTrace()
                null
            }
        }

    @Suppress("DEPRECATION")
    fun registerOrLinkByEmail(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val languageCode = Locale.getDefault().language
        val localizedContext = getLocalizedContext(application, languageCode)
        val email = _uiState.value.email
        val password = _uiState.value.password
        if (email.isBlank() || password.isBlank()) {
            onError(localizedContext.getString(R.string.error_email_password_empty))
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val methodsResult = auth.fetchSignInMethodsForEmail(email).await()
                val methods = methodsResult.signInMethods ?: emptyList()

                when {
                    "google.com" in methods && auth.currentUser != null -> {
                        val credential = EmailAuthProvider.getCredential(email, password)
                        auth.currentUser!!
                            .linkWithCredential(credential)
                            .addOnCompleteListener { task ->
                                _uiState.value = _uiState.value.copy(isLoading = false)
                                if (task.isSuccessful) {
                                    onSuccess()
                                } else {
                                    onError(
                                        task.exception?.localizedMessage
                                            ?: localizedContext.getString(R.string.error_link_failed)
                                    )
                                }
                            }
                    }

                    methods.isEmpty() -> {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                _uiState.value = _uiState.value.copy(isLoading = false)
                                if (task.isSuccessful) {
                                    val currentLanguage = Locale.getDefault().language
                                    auth.setLanguageCode(currentLanguage)
                                    auth.currentUser!!.sendEmailVerification()
                                    _verificationDialog.value = true
                                    onSuccess()
                                } else {
                                    val errorMessage = getFirebaseAuthErrorMessage(
                                        task.exception,
                                        localizedContext
                                    )
                                    onError(errorMessage)
                                }
                            }
                    }

                    else -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                        onError(localizedContext.getString(R.string.error_already_registered))
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                onError(
                    e.localizedMessage
                        ?: localizedContext.getString(R.string.error_fetch_sign_in_methods)
                )
            }
        }
    }

    fun monitorEmailVerification(onEmailVerified: () -> Unit) {
        viewModelScope.launch {
            var isVerified = false
            while (!isVerified) {
                val user = auth.currentUser
                user?.reload()?.addOnCompleteListener { task ->
                    if (task.isSuccessful && user.isEmailVerified) {
                        isVerified = true
                        viewModelScope.launch {
                            dataStoreManager.setUserLoggedIn(true)
                        }
                        onEmailVerified()
                    }
                }
                delay(5000)
            }
        }
    }

    private fun validatePassword(password: String): String {
        val languageCode = Locale.getDefault().language
        val localizedContext = getLocalizedContext(application, languageCode)
        val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d).{6,}$")
        val zxcvbn = Zxcvbn()
        return when {
            password.isEmpty() -> localizedContext.getString(R.string.password_empty)
            password.length < 6 -> localizedContext.getString(R.string.password_length)
            !regex.matches(password) -> localizedContext.getString(R.string.password_format)
            zxcvbn.measure(password).score < 2 -> localizedContext.getString(R.string.password_simple)
            else -> ""
        }
    }

    fun resetPassword() {
        AuthUtils.resetPassword(_uiState.value.email, application.applicationContext)
    }

    data class AuthUiState(
        val email: String = "",
        val password: String = "",
        val passwordVisibility: Boolean = false,
        val passwordError: String = "",
        val authErrorMessage: String = "",
        val isLoading: Boolean = false
    )

    fun getFirebaseAuthErrorMessage(exception: Exception?, context: Context): String {
        return when (exception) {
            is FirebaseAuthException -> {
                when (exception.errorCode) {
                    "ERROR_WRONG_PASSWORD" -> context.getString(R.string.error_wrong_password)
                    "ERROR_USER_NOT_FOUND" -> context.getString(R.string.error_user_not_found)
                    "ERROR_EMAIL_ALREADY_IN_USE" -> context.getString(R.string.error_email_already_used)
                    "ERROR_INVALID_EMAIL" -> context.getString(R.string.error_invalid_email)
                    "ERROR_INVALID_CREDENTIAL" -> context.getString(R.string.error_invalid_credential)
                    "ERROR_USER_DISABLED" -> context.getString(R.string.error_user_disabled)
                    "ERROR_WEAK_PASSWORD" -> context.getString(R.string.error_weak_password)
                    "ERROR_OPERATION_NOT_ALLOWED" -> context.getString(R.string.error_operation_not_allowed)
                    "ERROR_TOO_MANY_REQUESTS" -> context.getString(R.string.error_too_many_requests)
                    else -> context.getString(R.string.error_unknown)
                }
            }

            else -> exception?.localizedMessage ?: context.getString(R.string.error_unknown)
        }
    }
}


