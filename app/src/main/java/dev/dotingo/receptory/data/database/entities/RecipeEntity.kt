package dev.dotingo.receptory.data.database.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val recipeId: String = "",
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
