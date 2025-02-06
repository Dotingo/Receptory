package dev.dotingo.receptory.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class ReceptoryDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}