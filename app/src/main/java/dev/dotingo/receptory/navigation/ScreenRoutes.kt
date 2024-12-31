package dev.dotingo.receptory.navigation

import kotlinx.serialization.Serializable

@Serializable
object WelcomeScreenNav

@Serializable
object OnboardingScreenNav

@Serializable
object RegistrationScreenNav

@Serializable
object LoginScreenNav

@Serializable
data class MainScreenNav(
    val uid: String = ""
)

@Serializable
data class RecipeScreenNav(
    val recipeKey: String = ""
)

@Serializable
object EditRecipeScreenNav

@Serializable
object TimerScreenNav

@Serializable
object ShoppingListMenuScreenNav

@Serializable
object ShoppingListScreenNav

@Serializable
object SettingsScreenNav