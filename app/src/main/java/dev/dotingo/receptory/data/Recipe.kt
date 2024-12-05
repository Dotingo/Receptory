package dev.dotingo.receptory.data

data class Recipe(
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val imageUrl: String = "",
    val isLiked: Boolean = false,
    val rating: Int = 0,
    val cookingTime: String = "",
    val portions: String = "",
    val kcal: String = "",
    val ingredients: String = "",
    val websiteUrl: String = "",
    val videoUrl: String = ""
)
