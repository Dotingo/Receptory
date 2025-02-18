package dev.dotingo.receptory.presentation.screens.recipe_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.Recipe
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.presentation.screens.main_screen.toDomain
import dev.dotingo.receptory.presentation.screens.main_screen.toEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeScreenViewModel @Inject constructor(
    private val dao: RecipeDao
) : ViewModel() {

    // Состояние рецепта, которое будем обновлять по мере загрузки данных
    var recipe by mutableStateOf(Recipe())
        private set

    /**
     * Загружает рецепт из Firestore по ключу.
     */
//    fun fetchRecipe(key: String) {
//        val db: FirebaseFirestore = Firebase.firestore
//        db.collection("recipes")
//            .document(key)
//            .get()
//            .addOnSuccessListener { result ->
//                result.toObject(Recipe::class.java)?.let { fetchedRecipe ->
//                    recipe = fetchedRecipe
//                }
//            }
//            .addOnFailureListener {
//                // Обработка ошибки (например, можно вывести лог или установить какое-то состояние ошибки)
//            }
//    }

    fun fetchRecipe(key: String) {
        viewModelScope.launch {
            recipe = dao.getRecipeByKey(key)?.toDomain() ?: recipe
        }
    }


    fun toggleFavorite() {
        val newFavoriteValue = !recipe.favorite
        recipe = recipe.copy(favorite = newFavoriteValue)
        viewModelScope.launch {
            dao.updateRecipe(recipe.toEntity())
        }
    }
    /**
     * Переключает состояние «избранного» и обновляет его в Firestore.
     */
//    fun toggleFavorite(key: String) {
//        val newFavoriteValue = !recipe.favorite
//        // Обновляем локальное состояние сразу для мгновенного отклика UI
//        recipe = recipe.copy(favorite = newFavoriteValue)
//
//        val db: FirebaseFirestore = Firebase.firestore
//        db.collection("recipes").document(key)
//            .update("favorite", newFavoriteValue)
//            .addOnSuccessListener {
//                // Успешно обновлено, можно добавить дополнительную логику при необходимости
//            }
//            .addOnFailureListener {
//                // В случае ошибки возвращаем прежнее значение
//                recipe = recipe.copy(favorite = !newFavoriteValue)
//            }
//    }
}