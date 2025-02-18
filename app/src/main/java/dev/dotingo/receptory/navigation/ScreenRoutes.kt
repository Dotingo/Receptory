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
object MainScreenNav

@Serializable
data class RecipeScreenNav(
    val recipeKey: String = ""
)

@Serializable
data class  EditRecipeScreenNav(
    val recipeKey: String = ""
)

@Serializable
object TimerScreenNav

@Serializable
object ShoppingListMenuScreenNav

@Serializable
data class ShoppingListScreenNav(
    val shoppingListId: Long,
    val shoppingName: String
)

@Serializable
object SettingsScreenNav