package dev.dotingo.receptory.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import dev.dotingo.receptory.data.database.entities.ShoppingListEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert
    suspend fun insert(shoppingList: ShoppingListEntity): Long

    @Update
    suspend fun update(shoppingList: ShoppingListEntity)

    @Delete
    suspend fun delete(shoppingList: ShoppingListEntity)

    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getAllShoppingListsWithItemsFlow(): Flow<List<ShoppingListWithItems>>
}