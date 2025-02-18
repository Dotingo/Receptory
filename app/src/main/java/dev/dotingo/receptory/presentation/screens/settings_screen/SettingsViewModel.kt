package dev.dotingo.receptory.presentation.screens.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import dev.dotingo.receptory.di.ReceptoryApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStoreManager,
    private val application: ReceptoryApp,
    private val _firebaseAuth: FirebaseAuth
) : ViewModel(
) {
    private val _settingsState = MutableStateFlow(SettingsScreenState())
    val settingsState = _settingsState.asStateFlow()

    val auth = _firebaseAuth

    fun signOut() {
        viewModelScope.launch {
            dataStore.setUserLoggedIn(false)
        }
        _firebaseAuth.signOut()
    }

    fun handleScreenEvents(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.OpenThemeDialog -> {
                openTheme(event.open)
            }
            is SettingsScreenEvent.SetNewTheme -> setThemeValue(event.value)
            is SettingsScreenEvent.ThemeChanged -> getThemeValue()
        }
    }

    private fun openTheme(open: Boolean) {
        viewModelScope.launch {
            _settingsState.update { it.copy(openThemeDialog = open) }
        }
    }

    private fun setThemeValue(value: String) {
        viewModelScope.launch {
            dataStore.putThemeStrings(key = "theme", value = value)
            _settingsState.update { it.copy(getThemeValue = value) }
            application.theme.value = value
        }
    }

    private fun getThemeValue() {
        viewModelScope.launch {
            val theme = dataStore.getThemeStrings(key = "theme").first()
            theme?.let { value ->
                _settingsState.updateAndGet {
                    it.copy(getThemeValue = value)
                }
                application.theme.value = value
            }
        }
    }
}


data class SettingsScreenState(
    val openThemeDialog: Boolean = false,
    val getThemeValue: String? = null,
)

sealed interface SettingsScreenEvent {
    data class OpenThemeDialog(val open: Boolean = false) : SettingsScreenEvent
    data class SetNewTheme(val value: String) : SettingsScreenEvent
    data object ThemeChanged : SettingsScreenEvent
}