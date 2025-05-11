package dev.dotingo.receptory.presentation.screens.settings_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.database.entities.CategoryEntity
import dev.dotingo.receptory.data.datastore.DataStoreManager
import dev.dotingo.receptory.di.ReceptoryApp
import dev.dotingo.receptory.domain.repository.CategoryRepository
import dev.dotingo.receptory.domain.repository.RecipeRepository
import dev.dotingo.receptory.utils.AppLocaleManager
import dev.dotingo.receptory.utils.AuthUtils
import dev.dotingo.receptory.utils.getLocalizedContext
import dev.dotingo.receptory.work_manager.FirebaseUploadWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStoreManager,
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val application: ReceptoryApp,
    private val _firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories: StateFlow<List<CategoryEntity>> = _categories

    private val appLocaleManager = AppLocaleManager()
    private val _settingsState = MutableStateFlow(SettingsScreenState())
    val settingsState = _settingsState.asStateFlow()

    val auth = _firebaseAuth

    init {
        loadInitialLanguage()
        loadCategories()
    }

    private fun loadInitialLanguage() {
        val currentLanguage = appLocaleManager.getLanguageCode(context)
        _settingsState.value = _settingsState.value.copy(selectedLanguage = currentLanguage)
    }

    fun changeLanguage(languageCode: String) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        _settingsState.value = _settingsState.value.copy(selectedLanguage = languageCode)
    }

    private fun loadCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            val trimmedName = name.trim()
            val categoryExists =
                _categories.value.any { it.name.equals(trimmedName, ignoreCase = true) }

            if (categoryExists) {
                Toast.makeText(
                    application.applicationContext,
                    context.getString(R.string.category_already_exists),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val newCategory = CategoryEntity(
                    categoryId = "${UUID.randomUUID()}-${auth.uid}",
                    name = trimmedName
                )
                categoryRepository.insertCategory(newCategory)
                enqueueFirebaseSync()
            }
        }
    }

    fun updateCategory(category: CategoryEntity, newName: String) {
        val languageCode = Locale.getDefault().language
        val localizedContext = getLocalizedContext(application, languageCode)
        viewModelScope.launch {
            val trimmedNewName = newName.trim()
            val categoryExists = _categories.value.any {
                it.name.equals(
                    trimmedNewName,
                    ignoreCase = true
                ) && it.categoryId != category.categoryId
            }

            if (categoryExists) {
                Toast.makeText(
                    localizedContext,
                    localizedContext.getString(R.string.category_already_exists),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val updatedCategory = category.copy(name = trimmedNewName)
                categoryRepository.updateCategory(updatedCategory)
                updateRecipesCategory(category.name, trimmedNewName)
                enqueueFirebaseSync()
            }
        }
    }

    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch {
            categoryRepository.deleteCategory(category.categoryId)
            removeCategoryFromRecipes(category.name)
            enqueueFirebaseSync()
        }
    }

    private suspend fun updateRecipesCategory(oldCategory: String, newCategory: String) {
        val recipes = recipeRepository.getAllRecipes().first()
        recipes.forEach { recipe ->
            val updatedCategories = recipe.category
                .split(",")
                .map { it.trim() }
                .joinToString(", ") {
                    if (it == oldCategory.trim()) newCategory.trim() else it
                }

            val updatedRecipe = recipe.copy(category = updatedCategories)
            recipeRepository.updateRecipe(updatedRecipe)
        }
    }

    private suspend fun removeCategoryFromRecipes(categoryToRemove: String) {
        val recipes = recipeRepository.getAllRecipes().first()
        recipes.forEach { recipe ->
            val updatedCategories = recipe.category
                .split(",")
                .map { it.trim() }
                .filter { it != categoryToRemove.trim() }
                .joinToString(", ")
            val updatedRecipe = recipe.copy(category = updatedCategories)
            recipeRepository.updateRecipe(updatedRecipe)
        }
    }

    private fun enqueueFirebaseSync() {
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
    }

    @Suppress("DEPRECATION")
    fun signOut() {
        viewModelScope.launch {
            recipeRepository.deleteAllRecipes()
            categoryRepository.deleteAllCategories()
            dataStore.setUserLoggedIn(false)
        }
        _firebaseAuth.signOut()
        val token = context.getString(R.string.default_web_client_id)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        googleSignInClient.signOut()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SettingsViewModel", "Google sign-out successful")
                } else {
                    Log.w("SettingsViewModel", "Google sign-out failed", task.exception)
                }
            }
    }

    fun resetPassword(email: String) {
        AuthUtils.resetPassword(email, context)
    }

    fun handleScreenEvents(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.OpenThemeDialog -> openTheme(event.open)
            is SettingsScreenEvent.SetNewTheme -> setThemeValue(event.value)
            is SettingsScreenEvent.ThemeChanged -> getThemeValue()
        }
    }

    private fun openTheme(open: Boolean) {
        viewModelScope.launch {
            _settingsState.update { it.copy(openThemeDialog = open) }
        }
    }

    private fun setThemeValue(value: String) {
        viewModelScope.launch {
            dataStore.putThemeStrings(key = "theme", value = value)
            _settingsState.update { it.copy(getThemeValue = value) }
            application.theme.value = value
        }
    }

    private fun getThemeValue() {
        viewModelScope.launch {
            val storedTheme = dataStore.getThemeStrings(key = "theme").first()
            val theme = storedTheme ?: "Темная"
            _settingsState.update { it.copy(getThemeValue = theme) }
            application.theme.value = theme
        }
    }
}

data class SettingsScreenState(
    val openThemeDialog: Boolean = false,
    val getThemeValue: String = "Темная",
    val selectedLanguage: String = ""
)

sealed interface SettingsScreenEvent {
    data class OpenThemeDialog(val open: Boolean = false) : SettingsScreenEvent
    data class SetNewTheme(val value: String) : SettingsScreenEvent
    data object ThemeChanged : SettingsScreenEvent
}