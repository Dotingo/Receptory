package dev.dotingo.receptory.presentation.screens.edit_screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Immutable
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.FirebaseUploadWorker
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import dev.dotingo.receptory.data.local.repository.CategoryRepository
import dev.dotingo.receptory.di.ReceptoryApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val application: ReceptoryApp,
    private val auth: FirebaseAuth,
    private val recipeDao: RecipeDao,
    private val categoryRepository: CategoryRepository
) :
    ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories: StateFlow<List<CategoryEntity>> = _categories

    private val _uiState = MutableStateFlow(EditRecipeUiState())
    val uiState: StateFlow<EditRecipeUiState> = _uiState.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(true)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled.asStateFlow()

    private val _uid = MutableStateFlow("")
    val uid: StateFlow<String> = _uid.asStateFlow()

    private val _key = MutableStateFlow("")

    private var oldImageUrl: String? = null

    fun initialize(key: String) {
        _uid.value = auth.uid.toString()
        if (key.isNotEmpty()) {
            _key.value = key
            loadRecipeByKey(key)
        }
    }

    fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategories().collect { list ->
                _categories.value = list
            }
        }
    }

    fun saveCategory(name: String) {
        viewModelScope.launch {
            val exists = _categories.value.any { it.name.equals(name.trim(), ignoreCase = true) }
            if (!exists) {
                val category = CategoryEntity(
                    categoryId = "${UUID.randomUUID()}-${_uid.value}",
                    name = name
                )
                categoryRepository.insertCategory(category)
            } else {
                Toast.makeText(
                    application.applicationContext,
                    "Категория с таким названием уже существует",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun toggleCategory(categoryName: String) {
        val current = _uiState.value.selectedCategories.split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toMutableSet()

        if (current.contains(categoryName)) {
            current.remove(categoryName)
        } else {
            current.add(categoryName)
        }
        _uiState.value = _uiState.value.copy(selectedCategories = current.joinToString(", "))
    }

    fun setSelectedCategories(categories: List<String>) {
        _uiState.value = _uiState.value.copy(selectedCategories = categories.joinToString(", "))
    }

    private fun loadRecipeByKey(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeEntity = recipeDao.getRecipeByKey(key) // Метод нужно добавить в RecipeDao
            recipeEntity?.let { entity ->
                withContext(Dispatchers.Main) {
                    oldImageUrl = entity.imageUrl // Сохранение старого изображения
                    _uiState.value = EditRecipeUiState(
                        key = entity.recipeId,
                        title = entity.title,
                        description = entity.description,
                        cookingTime = entity.cookingTime,
                        portions = entity.portions,
                        kcal = entity.kcal,
                        ingredients = entity.ingredients,
                        cookingSteps = entity.cookingSteps,
                        siteLink = entity.websiteUrl,
                        videoLink = entity.videoUrl,
                        isFavorite = entity.favorite,
                        rating = entity.rating,
                        selectedCategories = entity.category,
                        selectedImageUri = entity.imageUrl.toUri()
                    )
                }
            }
        }
    }

    fun onFieldChange(field: EditRecipeField, value: String) {
        _uiState.update { state ->
            when (field) {
                EditRecipeField.TITLE -> state.copy(title = value)
                EditRecipeField.DESCRIPTION -> state.copy(description = value)
                EditRecipeField.COOKING_TIME -> state.copy(cookingTime = value)
                EditRecipeField.PORTIONS -> state.copy(portions = value)
                EditRecipeField.KCAL -> state.copy(kcal = value)
                EditRecipeField.INGREDIENTS -> state.copy(ingredients = value)
                EditRecipeField.COOKING_STEPS -> state.copy(cookingSteps = value)
                EditRecipeField.SITE_LINK -> state.copy(siteLink = value)
                EditRecipeField.VIDEO_LINK -> state.copy(videoLink = value)
                EditRecipeField.SELECTED_CATEGORIES -> state.copy(selectedCategories = value)
            }
        }
    }

    fun onFavoriteChange() {
        _uiState.update { state ->
            state.copy(isFavorite = !state.isFavorite)
        }
    }

    fun onRatingChange(newRating: Int) {
        _uiState.update { state ->
            state.copy(rating = newRating)
        }
    }

    fun onImageSelected(uri: Uri?) {
        _uiState.update { state ->
            state.copy(selectedImageUri = uri)
        }
        if (uri == null) {
            oldImageUrl = null
        }
        Log.d("URiLog", "onImageSelected: $uri")
        Log.d("URiLog", "onImageSelected: ${_uiState.value.selectedImageUri}")
    }

    fun saveRecipe(
        key: String,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        _key.value = key.ifEmpty { "${UUID.randomUUID()}-${_uid.value}" }
        _isButtonEnabled.value = false
        val state = _uiState.value
        val userId = auth.uid ?: ""

        val newImagePath = if (state.selectedImageUri != null) {
            if (state.selectedImageUri.path == oldImageUrl) {
                oldImageUrl ?: ""
            } else {
                saveCompressedRecipeImage()
            }
        } else {
            oldImageUrl ?: ""
        }

        if (state.selectedImageUri == null && !oldImageUrl.isNullOrEmpty()) {
            val oldImageFile = File(oldImageUrl)
            if (oldImageFile.exists()) {
                val deletionSuccessful = oldImageFile.delete()
                Log.d("FileDeletion", "Удаление файла прошло успешно: $deletionSuccessful")
            }
        }

        val recipe = RecipeEntity(
            recipeId = _key.value,
            userId = userId,
            title = state.title.trim(),
            description = state.description.trim(),
            category = state.selectedCategories,
            cookingSteps = state.cookingSteps.trim(),
            favorite = state.isFavorite,
            rating = state.rating,
            cookingTime = state.cookingTime.trim(),
            portions = state.portions.trim(),
            kcal = state.kcal.trim(),
            ingredients = state.ingredients.trim(),
            websiteUrl = state.siteLink.trim(),
            videoUrl = state.videoLink.trim(),
            imageUrl = newImagePath
        )
        saveRecipeToDatabase(recipe, onSaved, onError)
    }

    private fun saveCompressedRecipeImage(): String {
        val uri = _uiState.value.selectedImageUri ?: return ""
        val inputStream = when (uri.scheme) {
            "content" -> application.contentResolver.openInputStream(uri)
            "file" -> FileInputStream(uri.path?.let { File(it) })
            else -> null
        } ?: return ""

        val originalBitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()

        val fileName = "${UUID.randomUUID()}-${_uid.value}-${System.currentTimeMillis()}.jpg"
        val imageFile = File(application.filesDir, fileName)

        try {
            val outputStream = FileOutputStream(imageFile)
            originalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return imageFile.absolutePath
    }

    private fun saveRecipeToDatabase(
        recipe: RecipeEntity,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val existingRecipe = recipeDao.getRecipeByKey(recipe.recipeId)
                if (existingRecipe != null) {
                    recipeDao.updateRecipe(recipe) // Добавь метод update в DAO
                } else {
                    recipeDao.insertRecipe(recipe)
                }
                withContext(Dispatchers.Main) {
                    val constraints = Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()

                    val firebaseUploadWorkRequest =
                        OneTimeWorkRequestBuilder<FirebaseUploadWorker>()
                            .setConstraints(constraints)
                            .build()

                    WorkManager.getInstance(application.applicationContext).enqueueUniqueWork(
                        "firebase_sync",
                        ExistingWorkPolicy.REPLACE, // Если нужно заменить предыдущее задание, если оно ещё не выполнено
                        firebaseUploadWorkRequest
                    )
                    onSaved()
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }
}

@Immutable
data class EditRecipeUiState(
    val key: String = "",
    val title: String = "",
    val description: String = "",
    val cookingTime: String = "",
    val portions: String = "",
    val kcal: String = "",
    val ingredients: String = "",
    val cookingSteps: String = "",
    val siteLink: String = "",
    val videoLink: String = "",
    val isFavorite: Boolean = false,
    val rating: Int = 0,
    val selectedCategories: String = "",
    val selectedImageUri: Uri? = null
)

enum class EditRecipeField {
    TITLE, DESCRIPTION, COOKING_TIME, PORTIONS, KCAL, INGREDIENTS, COOKING_STEPS, SITE_LINK, VIDEO_LINK, SELECTED_CATEGORIES
}