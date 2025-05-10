package dev.dotingo.receptory.data.repository

import dev.dotingo.receptory.data.database.dao.ShoppingItemDao
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.domain.repository.ShoppingItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingItemRepositoryImpl @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao
) : ShoppingItemRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity): Long =
        shoppingItemDao.insert(shoppingItem)

    override suspend fun insertAllShoppingItems(shoppingItems: List<ShoppingItemEntity>): List<Long> =
        shoppingItemDao.insertAll(shoppingItems)

    override suspend fun updateShoppingItem(shoppingItem: ShoppingItemEntity) =
        shoppingItemDao.update(shoppingItem)

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItemEntity) =
        shoppingItemDao.delete(shoppingItem)

    override fun getItemsForShoppingListFlow(shoppingListId: Long): Flow<List<ShoppingItemEntity>> =
        shoppingItemDao.getItemsForShoppingListFlow(shoppingListId)

}
