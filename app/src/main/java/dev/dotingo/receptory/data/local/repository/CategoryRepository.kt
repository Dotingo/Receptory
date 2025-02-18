package dev.dotingo.receptory.data.local.repository

import dev.dotingo.receptory.data.local.database.dao.CategoryDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
){
    fun getAllCategories(): Flow<List<CategoryEntity>> =
        categoryDao.getAllCategories()

    suspend fun insertCategory(category: CategoryEntity) =
        categoryDao.insertCategory(category)
}