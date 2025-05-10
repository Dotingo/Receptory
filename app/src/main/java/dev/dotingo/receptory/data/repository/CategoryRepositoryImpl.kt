package dev.dotingo.receptory.data.repository

import dev.dotingo.receptory.data.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow
import dev.dotingo.receptory.data.database.dao.CategoryDao
import dev.dotingo.receptory.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getAllCategories()
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    override suspend fun insertAllCategories(categories: List<CategoryEntity>) {
        categoryDao.insertAllCategories(categories)
    }

    override suspend fun updateCategory(category: CategoryEntity) {
        categoryDao.updateCategory(category)
    }

    override suspend fun deleteCategory(key: String) {
        categoryDao.deleteCategory(key)
    }
    override suspend fun deleteAllCategories(){
        categoryDao.deleteAllCategories()
    }
}