package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.Recipe
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import dev.dotingo.receptory.data.local.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

fun Recipe.toEntity(): RecipeEntity {
    return RecipeEntity(
        recipeId = this.recipeKey,
        userId = this.userId,
        title = this.title,
        description = this.description,
        cookingSteps = this.cookingSteps,
        category = this.category,
        imageUrl = this.image,
        favorite = this.favorite,
        rating = this.rating,
        cookingTime = this.cookingTime,
        portions = this.portions,
        kcal = this.kcal,
        ingredients = this.ingredients,
        websiteUrl = this.websiteUrl,
        videoUrl = this.videoUrl,
        creationTime = this.creationTime
    )
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        recipeKey = this.recipeId,
        userId = this.userId,
        title = this.title,
        description = this.description,
        cookingSteps = this.cookingSteps,
        category = this.category,
        image = this.imageUrl,
        favorite = this.favorite,
        rating = this.rating,
        cookingTime = this.cookingTime,
        portions = this.portions,
        kcal = this.kcal,
        ingredients = this.ingredients,
        websiteUrl = this.websiteUrl,
        videoUrl = this.videoUrl,
        creationTime = this.creationTime
    )
}

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseAuth: FirebaseAuth,
    private val recipeDao: RecipeDao,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _recipesList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipesList: StateFlow<List<Recipe>> = _recipesList.asStateFlow()

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

    //    fun changeLike(
//        key: String,
//        isLiked: Boolean
//    ) {
//        firestore.collection("recipes")
//            .document(key)
//            .update("favorite", !isLiked)
//            .addOnSuccessListener {
//                val updatedRecipes = _recipesList.value.map { recipe ->
//                    if (recipe.recipeKey == key) recipe.copy(favorite = !isLiked) else recipe
//                }
//                _recipesList.value = updatedRecipes
//                applyCurrentSorting()
//            }
//    }
//
//    fun fetchAllRecipes(
//    ) {
//        val userId = firebaseAuth.currentUser?.uid
//        firestore.collection("recipes")
//            .whereEqualTo("userId", userId).get()
//            .addOnSuccessListener { result ->
//                val recipes = result.toObjects(Recipe::class.java)
//                _recipesList.value = result.toObjects(Recipe::class.java)
//                applyCurrentSorting()
//                viewModelScope.launch(Dispatchers.IO) {
//                    recipes.forEach { recipe ->
//                        recipeDao.insertRecipe(recipe.toEntity())
//                    }
//                }
//            }
//            .addOnFailureListener {
//                Log.d("MyLog", "${it.message}")
//            }
//    }
//
    fun fetchAllCategories() {
        viewModelScope.launch(Dispatchers.IO){
            categoryRepository.getAllCategories().collect{ list ->
                _categoriesList.value = list
            }
        }
    }
//    fun fetchAllCategories(
//    ) {
//        val userId = firebaseAuth.currentUser?.uid
//        firestore.collection("categories")
//            .whereEqualTo("userId", userId).get()
//            .addOnSuccessListener { result ->
//                _categoriesList.value = result.toObjects(Category::class.java)
//            }
//            .addOnFailureListener {
//                Log.d("MyLog", "${it.message}")
//            }
//    }
//
//    fun deleteRecipe(
//        key: String
//    ) {
//        firestore.collection("recipes")
//            .document(key)
//            .get()
//            .addOnSuccessListener { document ->
//                if (document.exists()) {
//                    val imageUrl = document.getString("imageUrl").orEmpty()
//
//                    firestore.collection("recipes")
//                        .document(key)
//                        .delete()
//                        .addOnSuccessListener {
//                            Log.d("Firestore", "Документ успешно удалён")
//                            if (imageUrl.isNotEmpty()) {
//                                val storageRef = firebaseStorage.getReferenceFromUrl(imageUrl)
//                                storageRef.delete()
//                                    .addOnSuccessListener {
//                                        Log.d("FirebaseStorage", "Изображение успешно удалено")
//                                    }
//                                    .addOnFailureListener { e ->
//                                        Log.e("FirebaseStorage", "Ошибка удаления изображения", e)
//                                    }
//                            }
//                            fetchAllRecipes()
//                            viewModelScope.launch {
//                                recipeDao.deleteRecipe(key)
//                            }
//                        }
//                        .addOnFailureListener { e ->
//                            Log.e("Firestore", "Ошибка удаления документа", e)
//                        }
//                } else {
//                    Log.e("Firestore", "Документ с ключом $key не найден")
//                }
//            }.addOnFailureListener { e ->
//                Log.e("Firestore", "Ошибка получения документа для удаления", e)
//            }
//    }

    fun fetchAllRecipes(
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.getAllRecipes().collect { recipeEntities ->
                val recipes = recipeEntities.map { it.toDomain() }
                _recipesList.value = recipes
                applyCurrentSorting()
            }
        }
    }

    fun deleteRecipe(
        key: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
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
}

enum class SortType {
    NAME, DATE, RATING, CALORIES
}