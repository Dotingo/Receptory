package dev.dotingo.receptory.presentation.screens.main_screen

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.work_manager.FirebaseUploadWorker
import dev.dotingo.receptory.R
import dev.dotingo.receptory.R.string.recipe_description
import dev.dotingo.receptory.constants.FirebaseConstants
import dev.dotingo.receptory.data.database.entities.CategoryEntity
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.domain.repository.CategoryRepository
import dev.dotingo.receptory.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val application: Application,
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val firebaseFirestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _recipesList = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val recipesList: StateFlow<List<RecipeEntity>> = _recipesList.asStateFlow()

    private val _categoriesList = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categoriesList: StateFlow<List<CategoryEntity>> = _categoriesList.asStateFlow()

    private val _isDescending = MutableStateFlow(true)
    val isDescending: StateFlow<Boolean> = _isDescending.asStateFlow()

    private val _currentSortType = MutableStateFlow(SortType.DATE)

    private val _isFavoriteFilterOn = MutableStateFlow(false)
    val isFavoriteFilterOn: StateFlow<Boolean> = _isFavoriteFilterOn.asStateFlow()

    private val _isSortFilterOpen = MutableStateFlow(false)
    val isSortFilterOpen: StateFlow<Boolean> = _isSortFilterOpen.asStateFlow()

    private val _isCategoryFilterOpen = MutableStateFlow(false)
    val isCategoryFilterOpen: StateFlow<Boolean> = _isCategoryFilterOpen.asStateFlow()


    private val _updatedRecipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val updatedRecipes: StateFlow<List<RecipeEntity>> = _updatedRecipes

    private fun areRecipesDifferentExcludingImage(server: RecipeEntity, local: RecipeEntity): Boolean {
        return server.copy(imageUrl = local.imageUrl) != local
    }

    fun clearUpdates() {
        _updatedRecipes.value = emptyList()
    }

    fun checkUpdatesNow() {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //рецепты
                val recipeSnapshot = firebaseFirestore.collection(FirebaseConstants.RECIPES)
                    .whereEqualTo(FirebaseConstants.USER_ID_FIELD, userId)
                    .get()
                    .await()
                val serverRecipeList = recipeSnapshot.toObjects(RecipeEntity::class.java)
                val serverRecipeMap = serverRecipeList.associateBy { it.recipeId }

                val localRecipeList = recipeRepository.getAllRecipes().first()
                val localRecipeMap = localRecipeList.associateBy { it.recipeId }

                val updatedOrNew = serverRecipeList.filter { server ->
                    localRecipeMap[server.recipeId]?.let { local ->
                        areRecipesDifferentExcludingImage(server, local)
                    } != false
                }

                val deleted = localRecipeList.filter { it.recipeId !in serverRecipeMap }

                _updatedRecipes.value = updatedOrNew + deleted

                //категории
                val categorySnapshot = firebaseFirestore.collection(FirebaseConstants.CATEGORIES)
                    .whereEqualTo(FirebaseConstants.USER_ID_FIELD, userId)
                    .get()
                    .await()
                val serverCategoryList = categorySnapshot.toObjects(CategoryEntity::class.java)
                val serverCategoryMap = serverCategoryList.associateBy { it.categoryId }

                val localCategoryList = categoryRepository.getAllCategories().first()
                val localCategoryMap = localCategoryList.associateBy { it.categoryId }

                val updatedOrNewCategories = serverCategoryList.filter { server ->
                    localCategoryMap[server.categoryId]?.let { local ->
                        server != local
                    } != false
                }

                val deletedCategories = localCategoryList.filter { it.categoryId !in serverCategoryMap }

                if (updatedOrNewCategories.isNotEmpty() || deletedCategories.isNotEmpty()) {
                    Log.d("MainVM", "Категории требуют обновления: ${updatedOrNewCategories.size} новых/обновленных, ${deletedCategories.size} удалённых")
                }

            } catch (e: Exception) {
                Log.e("MainVM", "checkUpdatesNow failed", e)
            }
        }
    }

    fun applyServerUpdates() {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //рецепты
                val recipeSnapshot = firebaseFirestore.collection(FirebaseConstants.RECIPES)
                    .whereEqualTo(FirebaseConstants.USER_ID_FIELD, userId)
                    .get()
                    .await()
                val serverRecipeList = recipeSnapshot.toObjects(RecipeEntity::class.java)
                val serverRecipeMap = serverRecipeList.associateBy { it.recipeId }

                val localRecipeList = recipeRepository.getAllRecipes().first()
                val localRecipeMap = localRecipeList.associateBy { it.recipeId }

                val toDelete = localRecipeList.filter { it.recipeId !in serverRecipeMap }
                toDelete.forEach {
                    recipeRepository.deleteRecipe(it.recipeId)
                }

                val toInsertOrUpdate = serverRecipeList.map { server ->
                    val localRecipe = localRecipeMap[server.recipeId]

                    if (server.imageUrl.startsWith("http")) {
                        val newFile = try {
                            URL(server.imageUrl).openStream().use { input ->
                                val file = File(
                                    application.filesDir,
                                    "${server.recipeId}-${System.currentTimeMillis()}.jpg"
                                )
                                FileOutputStream(file).use { output -> input.copyTo(output) }
                                file
                            }
                        } catch (e: Exception) {
                            Log.e("MainVM", "Image download failed: ${server.imageUrl}", e)
                            null
                        }

                        if (newFile != null) {
                            localRecipe?.imageUrl?.let { oldPath ->
                                if (oldPath.startsWith(application.filesDir.absolutePath)) {
                                    val oldFile = File(oldPath)
                                    if (oldFile.exists()) oldFile.delete()
                                }
                            }

                            server.copy(imageUrl = newFile.absolutePath)
                        } else {
                            server
                        }
                    } else {
                        server
                    }
                }

                recipeRepository.insertAllRecipes(toInsertOrUpdate)
                _updatedRecipes.value = emptyList()

                //категории
                val categorySnapshot = firebaseFirestore.collection(FirebaseConstants.CATEGORIES)
                    .whereEqualTo(FirebaseConstants.USER_ID_FIELD, userId)
                    .get()
                    .await()
                val serverCategoryList = categorySnapshot.toObjects(CategoryEntity::class.java)
                val serverCategoryMap = serverCategoryList.associateBy { it.categoryId }

                val localCategoryList = categoryRepository.getAllCategories().first()

                val toDeleteCategories = localCategoryList.filter { it.categoryId !in serverCategoryMap }
                toDeleteCategories.forEach {
                    categoryRepository.deleteCategory(it.categoryId)
                }

                categoryRepository.insertAllCategories(serverCategoryList)

            } catch (e: Exception) {
                Log.e("MainVM", "applyServerUpdates failed", e)
            }
        }
    }

    fun changeDescending(value: Boolean) {
        _isDescending.value = value
        applyCurrentSorting()
    }

    fun setSortType(sortType: SortType) {
        _currentSortType.value = sortType
        applyCurrentSorting()
    }

    private fun applyCurrentSorting() {
        when (_currentSortType.value) {
            SortType.DATE -> sortByDate()
            SortType.NAME -> sortByName()
            SortType.RATING -> sortByRating()
            SortType.CALORIES -> sortByCalories()
        }
    }

    fun changeFavFilter(value: Boolean) {
        _isFavoriteFilterOn.value = value
    }
    fun toggleSortFilter() {
        _isSortFilterOpen.value = !_isSortFilterOpen.value
    }
    fun changeCategoryFilter(value: Boolean) {
        _isCategoryFilterOpen.value = value
    }

    fun fetchAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategories().collect { list ->
                _categoriesList.value = list
            }
        }
    }

    fun fetchAllRecipes() {

        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.getAllRecipes().collect{ recipeEntities ->
                _recipesList.value = recipeEntities
                applyCurrentSorting()
            }
        }
    }

    fun deleteRecipe(
        key: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val firebaseUploadWorkRequest = OneTimeWorkRequestBuilder<FirebaseUploadWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(application.applicationContext).enqueueUniqueWork(
                "firebase_sync",
                ExistingWorkPolicy.REPLACE,
                firebaseUploadWorkRequest
            )
            recipeRepository.deleteRecipe(key)
        }
    }

    fun changeLike(
        key: String,
        isLiked: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeEntity = recipeRepository.getRecipeByKey(key)
            recipeEntity?.let {
                val updatedEntity = it.copy(favorite = !isLiked)
                recipeRepository.insertRecipe(updatedEntity)
            }
            applyCurrentSorting()
        }

    }

    private fun sortByDate() {
        _recipesList.value = if (!_isDescending.value) {
            _recipesList.value.sortedByDescending { it.creationTime }
        } else {
            _recipesList.value.sortedBy { it.creationTime }
        }
    }

    private fun sortByRating() {
        _recipesList.value = if (_isDescending.value) {
            _recipesList.value.sortedByDescending { it.rating }
        } else {
            _recipesList.value.sortedBy { it.rating }
        }
    }

    private fun sortByCalories() {
        _recipesList.value = if (_isDescending.value) {
            _recipesList.value.sortedByDescending { it.kcal }
        } else {
            _recipesList.value.sortedBy { it.kcal }
        }
    }

    private fun sortByName() {
        _recipesList.value = if (!_isDescending.value) {
            _recipesList.value.sortedByDescending { it.title }
        } else {
            _recipesList.value.sortedBy { it.title }
        }
    }

    fun shareRecipe(
        recipe: RecipeEntity,
        context: Context,
    ) {
        val resources = context.resources

        val titleLabel = resources.getString(R.string.name)
        val descriptionLabel = resources.getString(recipe_description)
        val categoryLabel = resources.getString(R.string.category)
        val cookingTimeLabel = resources.getString(R.string.cooking_time)
        val portionsLabel = resources.getString(R.string.portions)
        val caloriesLabel = resources.getString(R.string.calories)
        val ingredientsLabel = resources.getString(R.string.ingredients)
        val stepsLabel = resources.getString(R.string.cooking_steps)

        val recipeText = buildString {
            appendIfNotBlank("$titleLabel: ", "\"${recipe.title}\"")
            appendIfNotBlank("$descriptionLabel:\n", recipe.description)
            appendIfNotBlank("$categoryLabel: ", recipe.category)
            appendIfNotBlank("$cookingTimeLabel: ", recipe.cookingTime)
            appendIfNotBlank("$portionsLabel: ", recipe.portions)
            appendIfNotBlank("$caloriesLabel: ", recipe.kcal)

            if (recipe.ingredients.isNotBlank()) {
                append("\n🛒 $ingredientsLabel:\n")
                recipe.ingredients.lines()
                    .filter { it.isNotBlank() }
                    .forEach { append("- $it\n") }
            }

            if (recipe.cookingSteps.isNotBlank()) {
                append("\n👨‍🍳 $stepsLabel:\n")
                recipe.cookingSteps.lines()
                    .filter { it.isNotBlank() }
                    .forEachIndexed { index, step -> append("${index + 1}. $step\n") }
            }
        }

        val imagePath = if (recipe.imageUrl != "") File(recipe.imageUrl) else null

        val imageUri = imagePath?.let {
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                it
            )
        }

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, recipeText)
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "*/*"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpg", "image/png"))
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share recipe"))
    }
}

enum class SortType {
    NAME, DATE, RATING, CALORIES
}

fun StringBuilder.appendIfNotBlank(label: String, value: String) {
    if (value.isNotBlank()) append("$label$value\n")
}