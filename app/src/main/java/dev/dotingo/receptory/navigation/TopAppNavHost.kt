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
import dev.dotingo.receptory.presentation.screens.timer_screen.TimerScreen
import dev.dotingo.receptory.presentation.screens.WelcomeScreen
import dev.dotingo.receptory.presentation.screens.shopping_list_screen.ShoppingListScreen
import dev.dotingo.receptory.presentation.screens.shopping_list_screen.ShoppingListsMenuScreen

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
                navController.navigate(EditRecipeScreen) {
                    launchSingleTop = true
                }
            },
            navigateToShoppingListMenuScreen = {
                navController.navigate(ShoppingListMenuScreen) {
                    launchSingleTop = true
                }
            },
            navigateToTimerScreen = {
                navController.navigate(TimerScreen) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable<RecipeScreen> {
        RecipeScreen(
            navigateBack = {
                navigateBack(navController)
            },
            navigateToShoppingListMenuScreen = {
                navController.navigate(ShoppingListMenuScreen) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable<EditRecipeScreen> {
        EditRecipeScreen {
            navigateBack(navController)
        }
    }

    composable<ShoppingListMenuScreen> {
        ShoppingListsMenuScreen(
            navigateBack = {
                navigateBack(navController)
            },
            navigateToShoppingList = {
                navController.navigate(ShoppingListScreen) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable<ShoppingListScreen> {
        ShoppingListScreen {
            navigateBack(navController)
        }
    }

    composable<TimerScreen> {
        TimerScreen(navigateBack = {
            navigateBack(navController)
        })
    }
}

fun navigateBack(navController: NavHostController) {
    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED
    ) {
        navController.popBackStack()
    }
}