package dev.dotingo.receptory.domain.repository

import dev.dotingo.receptory.data.database.entities.ShoppingListEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity): Long
    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)
    fun getAllShoppingListsWithItemsFlow(): Flow<List<ShoppingListWithItems>>
}