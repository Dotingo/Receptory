package dev.dotingo.receptory.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.dotingo.receptory.presentation.MainViewModel
import dev.dotingo.receptory.presentation.screens.edit_screen.EditRecipeScreen
import dev.dotingo.receptory.presentation.screens.OnboardingScreen
import dev.dotingo.receptory.presentation.screens.RecipeScreen
import dev.dotingo.receptory.presentation.screens.SettingsScreen
import dev.dotingo.receptory.presentation.screens.WelcomeScreen
import dev.dotingo.receptory.presentation.screens.authorization.LoginScreen
import dev.dotingo.receptory.presentation.screens.authorization.RegistrationScreen
import dev.dotingo.receptory.presentation.screens.main_screen.MainScreen
import dev.dotingo.receptory.presentation.screens.shopping_list_screen.ShoppingListScreen
import dev.dotingo.receptory.presentation.screens.shopping_list_screen.ShoppingListsMenuScreen
import dev.dotingo.receptory.presentation.screens.timer_screen.TimerScreen

@Composable
fun TopAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel
) {
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsStateWithLifecycle()
    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsStateWithLifecycle()

    @Suppress("IMPLICIT_CAST_TO_ANY") val startDestination =
        if (isFirstLaunch) WelcomeScreenNav else if (!isUserLoggedIn) RegistrationScreenNav else MainScreenNav

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<WelcomeScreenNav> {
                WelcomeScreen(navigateToOnboardingScreen = {
                    navController.navigate(OnboardingScreenNav) {
                        launchSingleTop = true
                    }
                })
            }

            composable<OnboardingScreenNav> {
                OnboardingScreen(
                    navigateToRegistrationScreen = {
                        viewModel.setFirstLaunch(false)
                        navController.navigate(RegistrationScreenNav) {
                            launchSingleTop = true
                        }
                    })
            }

            composable<RegistrationScreenNav> {
                RegistrationScreen(
                    navigateToLoginScreen = {
                        navController.navigate(LoginScreenNav) {
                            launchSingleTop = true
                        }
                    },
                    navigateToMainScreen = {
                        navController.navigate(MainScreenNav) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<LoginScreenNav>(
            ) {
                LoginScreen(
                    navigateToRegistrationScreen = {
                        navigateBack(navController)
                    },
                    navigateToMainScreen = {
                        navController.navigate(MainScreenNav) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<MainScreenNav> {
                MainScreen(
                    navigateToRecipeScreen = { key ->
                        navController.navigate(RecipeScreenNav(key)) {
                            launchSingleTop = true
                        }
                    },
                    navigateToEditRecipeScreen = {
                        navController.navigate(EditRecipeScreenNav) {
                            launchSingleTop = true
                        }
                    },
                    navigateToShoppingListMenuScreen = {
                        navController.navigate(ShoppingListMenuScreenNav) {
                            launchSingleTop = true
                        }
                    },
                    navigateToTimerScreen = {
                        navController.navigate(TimerScreenNav) {
                            launchSingleTop = true
                        }
                    },
                    navigateToSettingsScreen = {
                        navController.navigate(SettingsScreenNav) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<RecipeScreenNav> { navEntry ->
                RecipeScreen(
                    key = navEntry.toRoute<RecipeScreenNav>().recipeKey,
                    navigateBack = {
                        navigateBack(navController)
                    },
                    navigateToShoppingListMenuScreen = {
                        navController.navigate(ShoppingListMenuScreenNav) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<EditRecipeScreenNav> {
                EditRecipeScreen {
                    navigateBack(navController)
                }
            }

            composable<ShoppingListMenuScreenNav> {
                ShoppingListsMenuScreen(
                    navigateBack = {
                        navigateBack(navController)
                    },
                    navigateToShoppingList = {
                        navController.navigate(ShoppingListScreenNav) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<ShoppingListScreenNav> {
                ShoppingListScreen {
                    navigateBack(navController)
                }
            }

            composable<TimerScreenNav> {
                TimerScreen(navigateBack = {
                    navigateBack(navController)
                })
            }

            composable<SettingsScreenNav> {
                SettingsScreen(navigateBack = {
                    navigateBack(navController)
                }, navigateToRegistrationScreen = {
                    navController.navigate(RegistrationScreenNav) {
                        launchSingleTop = true
                    }
                })
            }
        }
    }
}

fun navigateBack(navController: NavHostController) {
    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED
    ) {
        navController.popBackStack()
    }
}