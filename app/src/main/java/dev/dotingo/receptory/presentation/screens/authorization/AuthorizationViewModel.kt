package dev.dotingo.receptory.presentation.screens.authorization

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nulabinc.zxcvbn.Zxcvbn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.database.dao.CategoryDao
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import dev.dotingo.receptory.di.ReceptoryApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val application: ReceptoryApp,
    private val dataStoreManager: DataStoreManager,
    private val auth: FirebaseAuth,
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao,
    private val firebaseFirestore: FirebaseFirestore
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _verificationDialog = MutableStateFlow(false)
    val verificationDialog: StateFlow<Boolean> = _verificationDialog
    fun closeVerificationDialog() {
        _verificationDialog.value = false
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

    @OptIn(DelicateCoroutinesApi::class)
    fun signIn(onSuccessful: () -> Unit) {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            return
        }

        _uiState.value = state.copy(isLoading = true)
        auth.signInWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener { task ->
                _uiState.value = state.copy(isLoading = false)
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        viewModelScope.launch {
                            dataStoreManager.setUserLoggedIn(true)
                        }
                        val userId = auth.currentUser?.uid
                        Log.d("FirebaseDebug", "User ID: $userId")
                        viewModelScope.launch(SupervisorJob() + Dispatchers.IO){
                            try {
                                Log.d("FirebaseDebug", "Fetching recipes...")
                                val recipesSnapshot = firebaseFirestore.collection("recipes")
                                    .whereEqualTo("userId", userId)
                                    .get()
                                    .await()

                                Log.d("FirebaseDebug", "Recipes fetched: ${recipesSnapshot.documents.size}")

                                val recipes = recipesSnapshot.toObjects(RecipeEntity::class.java)

                                val categoriesSnapshot = firebaseFirestore.collection("categories")
                                    .whereEqualTo("userId", userId)
                                    .get()
                                    .await()

                                Log.d("FirebaseDebug", "Categories fetched: ${categoriesSnapshot.documents.size}")

                                val categories = categoriesSnapshot.toObjects(CategoryEntity::class.java)

                                // Обновляем рецепты: скачиваем изображение и сохраняем локально
                                val updatedRecipes = recipes.map { recipe ->
                                    // Предполагаем, что если ссылка начинается с "https://", это ссылка на Firebase Storage
                                    if (recipe.imageUrl.isNotEmpty() && recipe.imageUrl.startsWith("https://")) {
                                        Log.d("FirebaseDebug", "Downloading image: ${recipe.imageUrl}")
                                        val localFileName = "${recipe.recipeId}.jpg"
                                        val localPath = downloadImageAndSaveLocally(recipe.imageUrl, localFileName)
                                        if (localPath != null) {
                                            Log.d("FirebaseDebug", "Image saved locally: $localPath")
                                            recipe.copy(imageUrl = localPath)
                                        } else {
                                            Log.d("FirebaseDebug", "Image download failed: ${recipe.imageUrl}")
                                            recipe // Если не удалось скачать, оставляем оригинальный путь
                                        }
                                    } else {
                                        recipe
                                    }
                                }
                                Log.d("FirebaseDebug", "Inserting recipes into DB...")
                                recipeDao.insertAllRecipes(updatedRecipes)
                                categoryDao.insertAllCategories(categories)
                                Log.d("FirebaseDebug", "Data inserted successfully.")
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Log.e("FirebaseDebug", "Error: ${e.localizedMessage}")
                            }
                        }
                        onSuccessful()
                    } else {
                        _uiState.value = state.copy(
                            authErrorMessage = "Почта не подтверждена. Проверьте письмо для подтверждения."
                        )
                    }
                }
            }
            .addOnFailureListener {
                _uiState.value = state.copy(
                    isLoading = false,
                    authErrorMessage = it.localizedMessage ?: "Ошибка входа"
                )
            }
    }

    private suspend fun downloadImageAndSaveLocally(imageUrl: String, fileName: String): String? = withContext(
        Dispatchers.IO) {
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
            // Можно настроить качество по необходимости
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

    fun signUp() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            return
        }

        _uiState.value = state.copy(isLoading = true)
        auth.createUserWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener { task ->
                _uiState.value = state.copy(isLoading = false)
                if (task.isSuccessful && state.passwordError.isEmpty()) {
                    auth.currentUser!!.sendEmailVerification()
                    _verificationDialog.value = true
                }
            }.addOnFailureListener {
                _uiState.value = state.copy(
                    isLoading = false,
                    authErrorMessage = it.localizedMessage ?: "Ошибка регистрации"
                )
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
        val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d).{6,}\$")
        val zxcvbn = Zxcvbn()
        return when {
            password.isEmpty() -> "Пароль не должен быть пуст"
            password.length < 6 -> "Пароль должен содержать не менее 6 символов"
            !regex.matches(password) -> "Пароль должен содержать цифры и латинские буквы"
            zxcvbn.measure(password).score < 2 -> "Пароль слишком простой"
            else -> ""
        }
    }

    fun resetPassword(){
        if (_uiState.value.email.isNotEmpty()) {
            auth.sendPasswordResetEmail(_uiState.value.email)
                .addOnSuccessListener {
                    Toast.makeText(application.applicationContext, "Ссылка для сброса пароля отправлена", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.d("MyLog","Ошибка: ${e.localizedMessage}")
                }
        } else {
            Toast.makeText(application.applicationContext, "Введите email перед сбросом пароля", Toast.LENGTH_SHORT).show()
        }
    }

    data class AuthUiState(
        val email: String = "",
        val password: String = "",
        val passwordVisibility: Boolean = false,
        val passwordError: String = "",
        val authErrorMessage: String = "",
        val isLoading: Boolean = false
    )
}

