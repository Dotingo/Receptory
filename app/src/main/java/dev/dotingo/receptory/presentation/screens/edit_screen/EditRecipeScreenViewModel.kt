package dev.dotingo.receptory.presentation.screens.edit_screen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(EditRecipeUiState())
    val uiState: StateFlow<EditRecipeUiState> = _uiState.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(true)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled.asStateFlow()

    private val _uid = MutableStateFlow("")
    val uid: StateFlow<String> = _uid.asStateFlow()

    init {
        _uid.value = auth.uid.toString()
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
    }

    fun saveRecipe(
//        uri: Uri?,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        _isButtonEnabled.value = false
        val state = _uiState.value
        val userId = auth.uid
        val recipe = Recipe(
            userId = userId ?: "",
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
            videoUrl = state.videoLink.trim()
        )

        saveRecipeImage(
//            uri = uri,
            recipe = recipe,
            onSaved = onSaved,
            onError = onError
        )
    }

    private fun saveRecipeImage(
//        uri: Uri?,
        recipe: Recipe,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        val uri = _uiState.value.selectedImageUri
        val storageRef = storage.reference
            .child("recipe_images")
            .child("image_${UUID.randomUUID()}.jpg")
        if (uri != null) {
            val uploadTask = storageRef.putFile(uri)
            uploadTask
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { url ->
                        Log.d("MyLog", "$url")
                        saveRecipeToFirestore(
                            url = url.toString(),
                            recipe = recipe,
                            onSaved = onSaved,
                            onError = onError
                        )
                    }
                }
                .addOnFailureListener { ex ->
                    Log.d("MyLog", "${ex.stackTrace}")
                }
        } else {
            saveRecipeToFirestore(
                recipe = recipe,
                onSaved = onSaved,
                onError = onError
            )
        }
    }

    private fun saveRecipeToFirestore(
        url: String = "",
        recipe: Recipe,
        onSaved: () -> Unit,
        onError: () -> Unit
    ) {
        val fsPath = firestore.collection("recipes")
        val key = fsPath.document().id
        fsPath.document(key)
            .set(
                recipe.copy(
                    key = key,
                    imageUrl = url
                )
            )
            .addOnSuccessListener {
                onSaved()
            }.addOnFailureListener {
                Log.d("MyLog", it.message.toString())
                onError()
            }
    }
}

@Immutable
data class EditRecipeUiState(
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