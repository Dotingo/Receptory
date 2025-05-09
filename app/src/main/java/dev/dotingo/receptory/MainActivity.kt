package dev.dotingo.receptory

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.dotingo.receptory.di.ReceptoryApp
import dev.dotingo.receptory.navigation.TopAppNavHost
import dev.dotingo.receptory.presentation.MainViewModel
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var application: ReceptoryApp

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !viewModel.isFirstLaunchLoaded.value }

        enableEdgeToEdge()

        setContent {
            ReceptoryTheme(
                appTheme = application.theme.value
            ) {
                TopAppNavHost(
                    modifier = Modifier.padding(horizontal = commonHorizontalPadding),
                    viewModel = viewModel
                )
            }
        }
        lifecycleScope.launch {
            viewModel.initData()
        }
    }
}