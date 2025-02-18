package dev.dotingo.receptory.data

data class Recipe(
    val userId: String = "",
    val recipeKey: String = "",
    val title: String = "",
    val description: String = "",
    val cookingSteps: String = "",
    val category: String = "",
    val image: String = "",
    val favorite: Boolean = false,
    val rating: Int = 0,
    val cookingTime: String = "",
    val portions: String = "",
    val kcal: String = "",
    val ingredients: String = "",
    val websiteUrl: String = "",
    val videoUrl: String = "",
    val creationTime: Long = System.currentTimeMillis()
)
