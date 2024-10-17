package dev.dotingo.receptory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.dotingo.receptory.navigation.TopAppNavHost
import dev.dotingo.receptory.presentation.screens.LoginScreen
import dev.dotingo.receptory.presentation.screens.RegistrationScreen
import dev.dotingo.receptory.presentation.screens.WelcomeScreen
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReceptoryTheme {
                TopAppNavHost()
            }
        }
    }
}
