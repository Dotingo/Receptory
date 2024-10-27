package dev.dotingo.receptory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dotingo.receptory.presentation.screens.EditRecipeScreen
import dev.dotingo.receptory.presentation.screens.LoginScreen
import dev.dotingo.receptory.presentation.screens.main_screen.MainScreen
import dev.dotingo.receptory.presentation.screens.OnboardingScreen
import dev.dotingo.receptory.presentation.screens.RecipeScreen
import dev.dotingo.receptory.presentation.screens.RegistrationScreen
import dev.dotingo.receptory.presentation.screens.WelcomeScreen

@Composable
fun TopAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) = NavHost(
    navController = navController,
    startDestination = Welcome
) {
    composable<Welcome> {
        WelcomeScreen(navigateToOnboardingScreen = {
            navController.navigate(Onboarding) {
                launchSingleTop = true
            }
        })
    }

    composable<Onboarding> {
        OnboardingScreen(navigateToRegistrationScreen = {
            navController.navigate(Registration) {
                launchSingleTop = true
            }
        })
    }

    composable<Registration> {
        RegistrationScreen(
            navigateToLoginScreen = {
                navController.navigate(Login) {
                    launchSingleTop = true
                }
            },
            navigateToMainScreen = {
                navController.navigate(MainScreen) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable<Login> {
        LoginScreen(
            navigateToRegistrationScreen = {
                navigateBack(navController)
            },
            navigateToMainScreen = {
                navController.navigate(MainScreen) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable<MainScreen> {
        MainScreen(
            navigateToRecipeScreen = {
                navController.navigate(RecipeScreen) {
                    launchSingleTop = true
                }
            },
            navigateToAddRecipeScreen = {
                navController.navigate(EditRecipeScreen){
                    launchSingleTop
                }
            }
        )
    }

    composable<RecipeScreen> {
        RecipeScreen(
            navigateBack = {
                navigateBack(navController)
            }
        )
    }

    composable<EditRecipeScreen> {
        EditRecipeScreen {
            navigateBack(navController)
        }
    }
}

fun navigateBack(navController: NavHostController) {
    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED
    ) {
        navController.popBackStack()
    }
}