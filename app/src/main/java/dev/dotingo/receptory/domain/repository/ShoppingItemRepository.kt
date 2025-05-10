package dev.dotingo.receptory.domain.repository

import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingItemRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity): Long
    suspend fun insertAllShoppingItems(shoppingItems: List<ShoppingItemEntity>): List<Long>
    suspend fun updateShoppingItem(shoppingItem: ShoppingItemEntity)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItemEntity)
    fun getItemsForShoppingListFlow(shoppingListId: Long): Flow<List<ShoppingItemEntity>>
}