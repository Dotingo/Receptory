package dev.dotingo.receptory.domain.repository

import dev.dotingo.receptory.data.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<CategoryEntity>>

    suspend fun insertCategory(category: CategoryEntity)
    suspend fun insertAllCategories(categories: List<CategoryEntity>)

    suspend fun deleteAllCategories()

    suspend fun deleteCategory(categoryId: String)

    suspend fun updateCategory(category: CategoryEntity)
}