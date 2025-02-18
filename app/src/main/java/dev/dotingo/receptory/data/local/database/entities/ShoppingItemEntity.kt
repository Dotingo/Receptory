package dev.dotingo.receptory.data.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_item",
    foreignKeys = [ForeignKey(
        entity = ShoppingListEntity::class,
        parentColumns = ["shoppingListId"],
        childColumns = ["shoppingListId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("shoppingListId")]
)
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true) val itemId: Long = 0,
    val shoppingListId: Long,
    val name: String,
    val isPurchased: Boolean = false
)
