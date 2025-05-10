package dev.dotingo.receptory.data.repository

import dev.dotingo.receptory.data.database.dao.ShoppingListDao
import dev.dotingo.receptory.data.database.entities.ShoppingListEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListWithItems
import dev.dotingo.receptory.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {
    override fun getAllShoppingListsWithItemsFlow(): Flow<List<ShoppingListWithItems>> =
        shoppingListDao.getAllShoppingListsWithItemsFlow()

    override suspend fun insertShoppingList(shoppingList: ShoppingListEntity): Long =
        shoppingListDao.insert(shoppingList)

    override suspend fun updateShoppingList(shoppingList: ShoppingListEntity) =
        shoppingListDao.update(shoppingList)

    override suspend fun deleteShoppingList(shoppingList: ShoppingListEntity) =
        shoppingListDao.delete(shoppingList)
}