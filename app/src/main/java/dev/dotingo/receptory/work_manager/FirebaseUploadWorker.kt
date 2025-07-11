package dev.dotingo.receptory.work_manager

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.dotingo.receptory.constants.FirebaseConstants
import dev.dotingo.receptory.domain.repository.CategoryRepository
import dev.dotingo.receptory.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File

@HiltWorker
class FirebaseUploadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val firebaseFirestore: FirebaseFirestore
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "FirebaseUploadWorker"
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Log.e("FirebaseUploadWorker", "Пользователь не аутентифицирован")
            return@withContext Result.retry()
        }

        try {
            currentUser.getIdToken(true).await()
            Log.d(TAG, "Worker started")

            val localRecipes = recipeRepository.getAllRecipes().first()
                .map { it.copy(userId = currentUser.uid) }
            val localCategories = categoryRepository.getAllCategories().first()
                .map { it.copy(userId = currentUser.uid) }

            val localRecipeIds = localRecipes.map { it.recipeId }.toSet()
            val localCategoryIds = localCategories.map { it.categoryId }.toSet()

            val recipesDeferred = async {
                firebaseFirestore.collection(FirebaseConstants.RECIPES)
                    .whereEqualTo(FirebaseConstants.USER_ID_FIELD, currentUser.uid)
                    .get()
                    .await()
            }
            val categoriesDeferred = async {
                firebaseFirestore.collection(FirebaseConstants.CATEGORIES)
                    .whereEqualTo(FirebaseConstants.USER_ID_FIELD, currentUser.uid)
                    .get()
                    .await()
            }
            val cloudSnapshotRecipes = recipesDeferred.await()
            val cloudSnapshotCategories = categoriesDeferred.await()

            val batch = firebaseFirestore.batch()

            cloudSnapshotRecipes.documents.forEach { document ->
                if (document.id !in localRecipeIds) {
                    val docRef = firebaseFirestore.collection(FirebaseConstants.RECIPES).document(document.id)
                    batch.delete(docRef)
                    Log.d(TAG, "Deleted recipe: ${document.id}")

                    val imageRef =
                        FirebaseStorage.getInstance().reference.child("${FirebaseConstants.RECIPE_IMAGES}/${document.id}.jpg")
                    try {
                        imageRef.delete().await()
                        Log.d(TAG, "Deleted image: ${document.id}.jpg")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error deleting image", e)
                    }
                }
            }

            cloudSnapshotCategories.documents.forEach { document ->
                if (document.id !in localCategoryIds) {
                    val docRef = firebaseFirestore.collection(FirebaseConstants.CATEGORIES).document(document.id)
                    batch.delete(docRef)
                    Log.d(TAG, "Deleted category: ${document.id}")
                }
            }

            localRecipes.forEach { recipe ->
                var updatedRecipe = recipe
                if (recipe.imageUrl.isNotEmpty() && !recipe.imageUrl.startsWith("https://")) {
                    val imageFile = File(recipe.imageUrl)
                    if (imageFile.exists()) {
                        val fileUri = Uri.fromFile(imageFile)
                        val storageRef = FirebaseStorage.getInstance().reference
                            .child("${FirebaseConstants.RECIPE_IMAGES}/${recipe.recipeId}.jpg")
                        try {
                            storageRef.putFile(fileUri).await()

                            val downloadUrl = storageRef.downloadUrl.await().toString()

                            updatedRecipe = recipe.copy(imageUrl = downloadUrl)
                        } catch (e: Exception) {
                            Log.e(TAG, "Ошибка загрузки изображения", e)

                        }
                    }
                } else if (recipe.imageUrl.isEmpty()) {
                    val imageRef =
                        FirebaseStorage.getInstance().reference.child("${FirebaseConstants.RECIPE_IMAGES}/${recipe.recipeId}.jpg")
                    try {
                        imageRef.delete().await()
                        Log.d(TAG, "Deleted image: ${recipe.recipeId}.jpg")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error deleting image", e)
                    }
                }
                val docRef =
                    firebaseFirestore.collection(FirebaseConstants.RECIPES).document(updatedRecipe.recipeId)
                batch.set(docRef, updatedRecipe)
            }

            localCategories.forEach { category ->
                val docRef =
                    firebaseFirestore.collection(FirebaseConstants.CATEGORIES).document(category.categoryId)
                batch.set(docRef, category)
            }

            batch.commit().await()

            Log.d(TAG, "Sync completed successfully")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing data", e)
            Result.retry()
        }
    }
}