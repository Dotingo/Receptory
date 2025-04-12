package dev.dotingo.receptory.presentation.screens.main_screen

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.FirebaseUploadWorker
import dev.dotingo.receptory.R
import dev.dotingo.receptory.R.string.recipe_description
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import dev.dotingo.receptory.data.local.repository.CategoryRepository
import dev.dotingo.receptory.utils.appendIfNotBlank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val application: Application,
    private val recipeDao: RecipeDao,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _recipesList = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val recipesList: StateFlow<List<RecipeEntity>> = _recipesList.asStateFlow()

    private val _categoriesList = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categoriesList: StateFlow<List<CategoryEntity>> = _categoriesList.asStateFlow()

    private val _isDescending = MutableStateFlow(true)
    val isDescending: StateFlow<Boolean> = _isDescending.asStateFlow()
    fun changeDescending(value: Boolean) {
        _isDescending.value = value
        applyCurrentSorting()
    }

    private val _currentSortType = MutableStateFlow(SortType.DATE)
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

    private val _isFavoriteFilterOn = MutableStateFlow(false)
    val isFavoriteFilterOn: StateFlow<Boolean> = _isFavoriteFilterOn.asStateFlow()
    fun changeFavFilter(value: Boolean) {
        _isFavoriteFilterOn.value = value
    }

    private val _isSortFilterOpen = MutableStateFlow(false)
    val isSortFilterOpen: StateFlow<Boolean> = _isSortFilterOpen.asStateFlow()
    fun toggleSortFilter() {
        _isSortFilterOpen.value = !_isSortFilterOpen.value
    }

    private val _isCategoryFilterOpen = MutableStateFlow(false)
    val isCategoryFilterOpen: StateFlow<Boolean> = _isCategoryFilterOpen.asStateFlow()
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
            recipeDao.getAllRecipes().collect { recipeEntities ->
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
            recipeDao.deleteRecipe(key)
        }
    }

    fun changeLike(
        key: String,
        isLiked: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeEntity = recipeDao.getRecipeByKey(key)
            recipeEntity?.let {
                val updatedEntity = it.copy(favorite = !isLiked)
                recipeDao.insertRecipe(updatedEntity)
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
