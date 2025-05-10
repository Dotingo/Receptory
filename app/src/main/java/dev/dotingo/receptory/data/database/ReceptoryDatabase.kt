package dev.dotingo.receptory.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dotingo.receptory.data.database.dao.CategoryDao
import dev.dotingo.receptory.data.database.dao.RecipeDao
import dev.dotingo.receptory.data.database.dao.ShoppingItemDao
import dev.dotingo.receptory.data.database.dao.ShoppingListDao
import dev.dotingo.receptory.data.database.entities.CategoryEntity
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListEntity

@Database(
    entities = [RecipeEntity::class, CategoryEntity::class, ShoppingListEntity::class, ShoppingItemEntity::class],
    version = 4,
    exportSchema = false
)
abstract class ReceptoryDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun categoryDao(): CategoryDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingItemDao(): ShoppingItemDao
}