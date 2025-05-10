package dev.dotingo.receptory.data.repository

import dev.dotingo.receptory.data.database.dao.RecipeDao
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipeDao: RecipeDao) : RecipeRepository {
    override fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun insertAllRecipes(recipes: List<RecipeEntity>) {
        recipeDao.insertAllRecipes(recipes)
    }
    override suspend fun updateRecipe(recipe: RecipeEntity) = recipeDao.updateRecipe(recipe)
    override suspend fun deleteRecipe(recipeKey: String) = recipeDao.deleteRecipe(recipeKey)
    override suspend fun deleteAllRecipes() = recipeDao.deleteAllRecipes()
    override suspend fun getRecipeByKey(recipeKey: String): RecipeEntity? = recipeDao.getRecipeByKey(recipeKey)
}