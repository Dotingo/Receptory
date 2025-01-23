package dev.dotingo.receptory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch.asStateFlow()

    private val _isFirstLaunchLoaded = MutableStateFlow(false)
    val isFirstLaunchLoaded: StateFlow<Boolean> = _isFirstLaunchLoaded.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    suspend fun initData() {
        dataStoreManager.isFirstLaunchFlow().firstOrNull()?.let { value ->
            _isFirstLaunch.value = value
        }
        dataStoreManager.isUserLoggedIn().firstOrNull()?.let { value ->
            _isUserLoggedIn.value = value
        }
        _isFirstLaunchLoaded.value = true

//        getIsUserLoggedIn()
//        getIsFirstLaunch()
    }

//     private fun getIsFirstLaunch() {
//        viewModelScope.launch {
//            dataStoreManager.isFirstLaunchFlow().collect { value ->
//                _isFirstLaunch.value = value
//                _isFirstLaunchLoaded.value = true
//            }
//        }
//    }

    fun setFirstLaunch(isFirst: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setFirstLaunch(isFirst)
        }
    }

//    private fun getIsUserLoggedIn(){
//        viewModelScope.launch {
//            dataStoreManager.isUserLoggedIn().collect {value ->
//                _isUserLoggedIn.value = value
//            }
//        }
//    }

}