package dev.dotingo.receptory.data.database.entities

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Relation

@Keep
data class ShoppingListWithItems(
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "shoppingListId",
        entityColumn = "shoppingListId"
    )
    val items: List<ShoppingItemEntity>
)
