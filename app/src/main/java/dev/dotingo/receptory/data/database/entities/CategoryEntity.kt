package dev.dotingo.receptory.data.database.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val categoryId: String = "",
    val name: String = "",
    val userId: String = ""
)
