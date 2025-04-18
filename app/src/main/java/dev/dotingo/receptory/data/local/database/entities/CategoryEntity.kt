package dev.dotingo.receptory.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val categoryId: String = "",
    val name: String = "",
    val userId: String = ""
)
