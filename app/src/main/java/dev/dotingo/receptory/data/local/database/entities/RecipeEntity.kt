package dev.dotingo.receptory.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val recipeKey: String,
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val cookingSteps: String = "",
    val category: String = "",
    val imageUrl: String = "",
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
