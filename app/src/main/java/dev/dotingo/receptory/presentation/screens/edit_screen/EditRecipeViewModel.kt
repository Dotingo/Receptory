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
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.Recipe
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.data.local.repository.CategoryRepository
import dev.dotingo.receptory.di.ReceptoryApp
import dev.dotingo.receptory.presentation.screens.main_screen.toDomain
import dev.dotingo.receptory.presentation.screens.main_screen.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
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
                val recipe = entity.toDomain()
                withContext(Dispatchers.Main) {
                    oldImageUrl = recipe.image // Сохранение старого изображения
                    _uiState.value = EditRecipeUiState(
                        key = recipe.recipeKey,
                        title = recipe.title,
                        description = recipe.description,
                        cookingTime = recipe.cookingTime,
                        portions = recipe.portions,
                        kcal = recipe.kcal,
                        ingredients = recipe.ingredients,
                        cookingSteps = recipe.cookingSteps,
                        siteLink = recipe.websiteUrl,
                        videoLink = recipe.videoUrl,
                        isFavorite = recipe.favorite,
                        rating = recipe.rating,
                        selectedCategories = recipe.category,
                        selectedImageUri = recipe.image.toUri()
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
        // Если ключ пустой, генерируем новый
        _key.value = key.ifEmpty { "${UUID.randomUUID()}-${_uid.value}" }
        _isButtonEnabled.value = false
        val state = _uiState.value
        val userId = auth.uid ?: ""

        // Если выбрано новое изображение, сохраняем его и получаем новый путь,
        // иначе используем старый путь (если изображение не менялось)
        val newImagePath = if (state.selectedImageUri != null) {
            saveCompressedRecipeImage()
        } else {
            ""
        }

        // Если пользователь выбрал новое изображение и старый путь существует,
        // удаляем старый файл (если он отличается от нового)
        if (state.selectedImageUri == null && !oldImageUrl.isNullOrEmpty()) {
            val oldImageFile = File(oldImageUrl)
            if (oldImageFile.exists()) {
                val deletionSuccessful = oldImageFile.delete()
                Log.d("FileDeletion", "Удаление файла прошло успешно: $deletionSuccessful")
            }
        }

        val recipe = Recipe(
            recipeKey = _key.value,
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
            image = newImagePath
        )
        saveRecipeToDatabase(recipe, onSaved, onError)
    }

    private fun saveCompressedRecipeImage(): String {
        val uri = _uiState.value.selectedImageUri
        if (uri != null) {
            // Получаем InputStream исходного изображения
            val inputStream = application.contentResolver.openInputStream(uri)
            // Декодируем поток в Bitmap
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            // Генерируем уникальное имя файла для изображения
            val fileName = "${UUID.randomUUID()}-${_uid.value}-${System.currentTimeMillis()}.jpg"
            // Формируем File во внутреннем хранилище приложения
            val imageFile = File(application.filesDir, fileName)

            try {
                // Открываем поток для записи в файл
                val outputStream = FileOutputStream(imageFile)
                // Сжимаем изображение и записываем в файл (JPEG, качество 50)
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
                outputStream.flush()
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }

            // Возвращаем абсолютный путь к сохранённому файлу, который можно записать в базу данных
            return imageFile.absolutePath
        }
        return ""
    }

    private fun saveRecipeToDatabase(
        recipe: Recipe,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val existingRecipe = recipeDao.getRecipeByKey(recipe.recipeKey)
                if (existingRecipe != null) {
                    recipeDao.updateRecipe(recipe.toEntity()) // Добавь метод update в DAO
                } else {
                    recipeDao.insertRecipe(recipe.toEntity())
                }
                withContext(Dispatchers.Main) {
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

//old code

//    fun initialize(key: String) {
//        if (key.isNotEmpty()) {
//            loadRecipe(key)
//        }
//    }

//    private fun loadRecipe(key: String) {
//        firestore.collection("recipes").document(key)
//            .get()
//            .addOnSuccessListener { document ->
//                if (document != null && document.exists()) {
//                    val recipe = document.toObject(Recipe::class.java)
//                    recipe?.let {
//                        oldImageUrl = it.imageUrl
//                        _uiState.value = _uiState.value.copy(
//                            title = it.title,
//                            description = it.description,
//                            cookingTime = it.cookingTime,
//                            portions = it.portions,
//                            kcal = it.kcal,
//                            ingredients = it.ingredients,
//                            cookingSteps = it.cookingSteps,
//                            siteLink = it.websiteUrl,
//                            videoLink = it.videoUrl,
//                            isFavorite = it.favorite,
//                            rating = it.rating,
//                            selectedCategories = it.category,
//                            selectedImageUri = it.imageUrl.let { uri -> Uri.parse(uri) },
//                            key = recipe.recipeKey
//                        )
//                        _key.value = key
//                    }
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("EditRecipeViewModel", "Failed to load recipe: ${exception.message}")
//            }
//    }

//    private fun getAllCategories(
//        onCategories: (List<Category>) -> Unit
//    ) {
//        firestore.collection("categories")
//            .whereEqualTo("userId", _uid).get()
//            .addOnSuccessListener { result ->
//                onCategories(result.toObjects(Category::class.java))
//            }
//            .addOnFailureListener {
//                Log.d("MyLog", "${it.message}")
//            }
//    }

//        saveRecipeImage(
//            recipe = recipe,
//            onSaved = onSaved,
//            onError = onError
//        )


//    private fun getAllCategories(
//        onCategories: (List<Category>) -> Unit
//    ) {
//        firestore.collection("categories")
//            .whereEqualTo("userId", _uid).get()
//            .addOnSuccessListener { result ->
//                onCategories(result.toObjects(Category::class.java))
//            }
//            .addOnFailureListener {
//                Log.d("MyLog", "${it.message}")
//            }
//    }

//    fun saveRecipe(
//        onSaved: () -> Unit,
//        onError: () -> Unit
//    ) {
//        _isButtonEnabled.value = false
//        val state = _uiState.value
//        val userId = auth.uid
//        val recipe = Recipe(
//            userId = userId ?: "",
//            title = state.title.trim(),
//            description = state.description.trim(),
//            category = state.selectedCategories,
//            cookingSteps = state.cookingSteps.trim(),
//            favorite = state.isFavorite,
//            rating = state.rating,
//            cookingTime = state.cookingTime.trim(),
//            portions = state.portions.trim(),
//            kcal = state.kcal.trim(),
//            ingredients = state.ingredients.trim(),
//            websiteUrl = state.siteLink.trim(),
//            videoUrl = state.videoLink.trim(),
//            recipeKey = _key.value
//        )
//        saveRecipeImage(
//            recipe = recipe,
//            onSaved = onSaved,
//            onError = onError
//        )
//    }
//
//    private fun saveRecipeImage(
//        recipe: Recipe,
//        onSaved: () -> Unit,
//        onError: () -> Unit
//    ) {
//        val uri = _uiState.value.selectedImageUri
//
//        // Сценарий 1: Пользователь убрал картинку (uri == null) и ранее была загруженная картинка
//        if (uri == null && !oldImageUrl.isNullOrEmpty()) {
//            storage.getReferenceFromUrl(oldImageUrl!!)
//                .delete()
//                .addOnSuccessListener {
//                    Log.d("EditRecipeViewModel", "Old image deleted successfully")
//                }
//                .addOnFailureListener { e ->
//                    Log.e("EditRecipeViewModel", "Failed to delete old image: ${e.message}")
//                }
//            // Обновляем рецепт с пустым imageUrl
//            saveRecipeToFirestore(
//                url = "",
//                recipe = recipe,
//                onSaved = onSaved,
//                onError = onError
//            )
//        }
//        // Сценарий 2: Пользователь выбрал новую картинку (uri != null и не начинается с "http")
//        else if (uri != null && !uri.toString().startsWith("http")) {
//            // Если ранее была установлена картинка, удаляем её
//            oldImageUrl?.takeIf { it.isNotEmpty() }?.let { url ->
//                storage.getReferenceFromUrl(url)
//                    .delete()
//                    .addOnSuccessListener {
//                        Log.d("EditRecipeViewModel", "Old image deleted successfully")
//                    }
//                    .addOnFailureListener { e ->
//                        Log.e("EditRecipeViewModel", "Failed to delete old image: ${e.message}")
//                    }
//            }
//            val storageRef = storage.reference
//                .child("recipe_images")
//                .child("image_${UUID.randomUUID()}-${_uid.value}.jpg")
//            val uploadTask = storageRef.putFile(uri)
//            uploadTask
//                .addOnSuccessListener {
//                    storageRef.downloadUrl.addOnSuccessListener { url ->
//                        saveRecipeToFirestore(
//                            url = url.toString(),
//                            recipe = recipe,
//                            onSaved = onSaved,
//                            onError = onError
//                        )
//                    }
//                }
//                .addOnFailureListener { ex ->
//                    Log.e("EditRecipeViewModel", "Image upload failed: ${ex.message}")
//                    onError()
//                }
//        }
//        // Сценарий 3: Пользователь не изменил изображение (uri != null и уже содержит URL картинки)
//        else {
//            // Если изображение не изменялось, но старое изображение требуется удалить (например, пользователь решил убрать его),
//            // можно проверить и удалить его, если оно все ещё хранится.
//            if (uri != null && uri.toString().startsWith("http") && _uiState.value.selectedImageUri == null) {
//                // Если вдруг состояние пришло с uri == null, а предыдущий URL не пустой, удаляем старое изображение
//                oldImageUrl?.takeIf { it.isNotEmpty() }?.let { url ->
//                    storage.getReferenceFromUrl(url)
//                        .delete()
//                        .addOnSuccessListener {
//                            Log.d("EditRecipeViewModel", "Old image deleted successfully")
//                        }
//                        .addOnFailureListener { e ->
//                            Log.e("EditRecipeViewModel", "Failed to delete old image: ${e.message}")
//                        }
//                }
//                saveRecipeToFirestore(
//                    url = "",
//                    recipe = recipe,
//                    onSaved = onSaved,
//                    onError = onError
//                )
//            } else {
//                // Если изображение не изменялось, просто используем существующий URL
//                saveRecipeToFirestore(
//                    url = uri?.toString() ?: "",
//                    recipe = recipe,
//                    onSaved = onSaved,
//                    onError = onError
//                )
//            }
//        }
//    }
//
//    private fun saveRecipeToFirestore(
//        url: String = "",
//        recipe: Recipe,
//        onSaved: () -> Unit,
//        onError: () -> Unit
//    ) {
//        val fsPath = firestore.collection("recipes")
//        val recipeKey = recipe.recipeKey
//
//        if (recipeKey.isNotEmpty()) {
//            fsPath.document(recipeKey)
//                .update(
//                    mapOf(
//                        "title" to recipe.title,
//                        "description" to recipe.description,
//                        "category" to recipe.category,
//                        "cookingSteps" to recipe.cookingSteps,
//                        "favorite" to recipe.favorite,
//                        "rating" to recipe.rating,
//                        "cookingTime" to recipe.cookingTime,
//                        "portions" to recipe.portions,
//                        "kcal" to recipe.kcal,
//                        "ingredients" to recipe.ingredients,
//                        "websiteUrl" to recipe.websiteUrl,
//                        "videoUrl" to recipe.videoUrl,
//                        "imageUrl" to url
//                    )
//                )
//                .addOnSuccessListener {
//                    onSaved()
//                }
//                .addOnFailureListener {
//                    Log.d("MyLog", it.message.toString())
//                    onError()
//                }
//        } else {
//            val newKey = fsPath.document().id
//            fsPath.document(newKey)
//                .set(
//                    recipe.copy(
//                        recipeKey = newKey,
//                        imageUrl = url
//                    )
//                )
//                .addOnSuccessListener {
//                    onSaved()
//                }
//                .addOnFailureListener {
//                    Log.d("MyLog", it.message.toString())
//                    onError()
//                }
//        }
//    }