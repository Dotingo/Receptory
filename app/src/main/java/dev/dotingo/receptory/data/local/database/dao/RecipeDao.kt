package dev.dotingo.receptory.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    // Вставка рецепта. При конфликте (например, уже существует запись с таким ключом) перезаписываем данные
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipes: List<RecipeEntity>)

    // Обновление рецепта
    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    // Удаление рецепта
    @Query("DELETE FROM recipes WHERE recipeId = :recipeKey")
    suspend fun deleteRecipe(recipeKey: String)

    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()

    // Получение отдельного рецепта по ключу
    @Query("SELECT * FROM recipes WHERE recipeId = :recipeKey LIMIT 1")
    suspend fun getRecipeByKey(recipeKey: String): RecipeEntity?
}