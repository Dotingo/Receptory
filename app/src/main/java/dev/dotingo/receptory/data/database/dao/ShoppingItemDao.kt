package dev.dotingo.receptory.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Insert
    suspend fun insert(shoppingItem: ShoppingItemEntity): Long

    @Insert
    suspend fun insertAll(shoppingItems: List<ShoppingItemEntity>): List<Long>

    @Update
    suspend fun update(shoppingItem: ShoppingItemEntity)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItemEntity)

    @Query("SELECT * FROM shopping_item WHERE shoppingListId = :shoppingListId")
    fun getItemsForShoppingListFlow(shoppingListId: Long): Flow<List<ShoppingItemEntity>>
}