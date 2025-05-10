package dev.dotingo.receptory.domain.repository

import dev.dotingo.receptory.data.database.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAllRecipes(): Flow<List<RecipeEntity>>
    suspend fun insertRecipe(recipe: RecipeEntity)
    suspend fun insertAllRecipes(recipes: List<RecipeEntity>)
    suspend fun updateRecipe(recipe: RecipeEntity)
    suspend fun deleteRecipe(recipeKey: String)
    suspend fun deleteAllRecipes()
    suspend fun getRecipeByKey(recipeKey: String): RecipeEntity?
}