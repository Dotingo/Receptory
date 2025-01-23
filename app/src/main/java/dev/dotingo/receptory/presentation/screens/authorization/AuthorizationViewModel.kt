package dev.dotingo.receptory.presentation.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nulabinc.zxcvbn.Zxcvbn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val auth: FirebaseAuth
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

    fun signOut() {
        auth.signOut()
    }

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
                        onSuccessful()
                    } else {
                        _uiState.value = state.copy(
                            authErrorMessage = "Почта не подтверждена. Проверьте письмо для подтверждения."
                        )
                    }
                }
            }.addOnFailureListener {
                _uiState.value = state.copy(
                    isLoading = false,
                    authErrorMessage = it.localizedMessage ?: "Ошибка входа"
                )
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
        val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@\$!%*?&]{8,}\$")
        val zxcvbn = Zxcvbn()
        return when {
            password.isEmpty() -> "Пароль не должен быть пуст"
            password.length < 8 -> "Пароль должен содержать не менее 8 символов"
            !regex.matches(password) -> "Пароль должен содержать цифры, прописные и строчные латинские буквы"
            zxcvbn.measure(password).score < 3 -> "Пароль слишком простой"
            else -> ""
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

