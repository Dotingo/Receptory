package dev.dotingo.receptory.di

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ReceptoryApp: Application() {
    val theme = mutableStateOf("")
}