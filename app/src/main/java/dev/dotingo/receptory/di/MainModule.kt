package dev.dotingo.receptory.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.dotingo.receptory.data.local.database.ReceptoryDatabase
import dev.dotingo.receptory.data.local.database.dao.CategoryDao
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.dao.ShoppingItemDao
import dev.dotingo.receptory.data.local.database.dao.ShoppingListDao
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager{
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ReceptoryDatabase {
        return Room.databaseBuilder(
            context,
            ReceptoryDatabase::class.java,
            "receptory_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRecipeDao(database: ReceptoryDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    fun provideCategoryDao(database: ReceptoryDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideShoppingListDao(database: ReceptoryDatabase): ShoppingListDao {
        return database.shoppingListDao()
    }
    @Provides
    fun provideShoppingItemDao(database: ReceptoryDatabase): ShoppingItemDao {
        return database.shoppingItemDao()
    }

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ReceptoryApp {
        return app as ReceptoryApp
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}