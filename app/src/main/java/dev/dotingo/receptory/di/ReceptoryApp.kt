package dev.dotingo.receptory.di

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ReceptoryApp : Application(), Configuration.Provider{

    @Inject lateinit var workerFactory : HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()

    val theme = mutableStateOf("")
}