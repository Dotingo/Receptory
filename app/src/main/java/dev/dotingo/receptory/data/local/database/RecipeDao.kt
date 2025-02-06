package dev.dotingo.receptory.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    // Вставка рецепта. При конфликте (например, уже существует запись с таким ключом) перезаписываем данные
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    // Удаление рецепта
    @Query("DELETE FROM recipes WHERE recipeKey = :recipeKey")
    suspend fun deleteRecipe(recipeKey: String)

    // Получение отдельного рецепта по ключу
    @Query("SELECT * FROM recipes WHERE recipeKey = :recipeKey LIMIT 1")
    suspend fun getRecipeByKey(recipeKey: String): RecipeEntity?
}