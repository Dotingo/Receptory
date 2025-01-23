package dev.dotingo.receptory.presentation.screens.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dev.dotingo.receptory.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {
    private val firestore = Firebase.firestore
    private val firebaseStorage = Firebase.storage

    private val _recipesListState = MutableStateFlow<List<Recipe>>(emptyList())
    val recipesListState: StateFlow<List<Recipe>> = _recipesListState.asStateFlow()

    private val _isFavoriteFilterOn = MutableStateFlow(false)
    val isFavoriteFilterOn: StateFlow<Boolean> = _isFavoriteFilterOn.asStateFlow()
    fun changeFavFilter(value: Boolean){
        _isFavoriteFilterOn.value = value
    }

    private val _isSortFilterOpen = MutableStateFlow(false)
    val isSortFilterOpen: StateFlow<Boolean> = _isSortFilterOpen.asStateFlow()
    fun changeSortFilter(value: Boolean){
        _isSortFilterOpen.value = value
    }

    fun changeLike(
        key: String,
        isLiked: Boolean
    ) {
        firestore.collection("recipes")
            .document(key)
            .update("favorite", !isLiked)
            .addOnSuccessListener {
                val updatedRecipes = _recipesListState.value.map { recipe ->
                    if (recipe.key == key) recipe.copy(favorite = !isLiked) else recipe
                }
                _recipesListState.value = updatedRecipes
            }
    }

    fun fetchAllRecipes(
    ) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        firestore.collection("recipes")
            .whereEqualTo("userId", userId).get()
            .addOnSuccessListener { result ->
                _recipesListState.value = result.toObjects(Recipe::class.java)
            }
            .addOnFailureListener {
                Log.d("MyLog", "${it.message}")
            }
    }

    fun deleteRecipe(
        key: String
    ) {
        firestore.collection("recipes")
            .document(key)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val imageUrl = document.getString("imageUrl").orEmpty()

                    firestore.collection("recipes")
                        .document(key)
                        .delete()
                        .addOnSuccessListener {
                            Log.d("Firestore", "Документ успешно удалён")

                            if (imageUrl.isNotEmpty()) {
                                val storageRef = firebaseStorage.getReferenceFromUrl(imageUrl)
                                storageRef.delete()
                                    .addOnSuccessListener {
                                        Log.d("FirebaseStorage", "Изображение успешно удалено")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.e("FirebaseStorage", "Ошибка удаления изображения", e)
                                    }
                            }

                            fetchAllRecipes()
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "Ошибка удаления документа", e)
                        }
                } else {
                    Log.e("Firestore", "Документ с ключом $key не найден")
                }
            }.addOnFailureListener { e ->
                Log.e("Firestore", "Ошибка получения документа для удаления", e)
            }
    }
}