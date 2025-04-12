package dev.dotingo.receptory.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import dev.dotingo.receptory.di.ReceptoryApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val application: Application
) : ViewModel() {
    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch.asStateFlow()

    private val _isFirstLaunchLoaded = MutableStateFlow(false)
    val isFirstLaunchLoaded: StateFlow<Boolean> = _isFirstLaunchLoaded.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    private val _themeValue = MutableStateFlow("Темная")

    suspend fun initData() {
        // Загружаем другие данные
        dataStoreManager.isFirstLaunchFlow().firstOrNull()?.let { value ->
            _isFirstLaunch.value = value
        }
        dataStoreManager.isUserLoggedIn().firstOrNull()?.let { value ->
            _isUserLoggedIn.value = value
        }
        // Загружаем тему
        val storedTheme = dataStoreManager.getThemeStrings("theme").first() ?: "Темная"
        _themeValue.value = storedTheme
        (getApplication(application) as ReceptoryApp).theme.value = storedTheme  // Если application доступно
        _isFirstLaunchLoaded.value = true
    }

    fun setFirstLaunch(isFirst: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setFirstLaunch(isFirst)
        }
    }
}