package dev.dotingo.receptory.data.database.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "shopping_list")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true) val shoppingListId: Long = 0,
    val name: String
)
