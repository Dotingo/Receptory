package dev.dotingo.receptory.data.local.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingListWithItems(
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "shoppingListId",
        entityColumn = "shoppingListId"
    )
    val items: List<ShoppingItemEntity>
)
