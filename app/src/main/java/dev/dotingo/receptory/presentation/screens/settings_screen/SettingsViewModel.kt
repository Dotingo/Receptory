package dev.dotingo.receptory.presentation.screens.settings_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.dotingo.receptory.data.local.database.dao.CategoryDao
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.data.local.datastore.DataStoreManager
import dev.dotingo.receptory.di.ReceptoryApp
import dev.dotingo.receptory.utils.AppLocaleManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStoreManager,
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao,
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
        appLocaleManager.changeLanguage(context, languageCode)
        _settingsState.value = _settingsState.value.copy(selectedLanguage = languageCode)
//        appLocaleManager.restartApp(context)
    }

    private fun loadCategories() {
        viewModelScope.launch {
            categoryDao.getAllCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            val newCategory = CategoryEntity(categoryId = "${UUID.randomUUID()}-${auth.uid}", name = name)
            categoryDao.insertCategory(newCategory)
        }
    }

    fun updateCategory(category: CategoryEntity, newName: String) {
        viewModelScope.launch {
            val updatedCategory = category.copy(name = newName)
            categoryDao.updateCategory(updatedCategory)
            updateRecipesCategory(category.name, newName)
        }
    }

    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch {
            categoryDao.deleteCategory(category.categoryId)
            removeCategoryFromRecipes(category.name)
        }
    }

    private suspend fun updateRecipesCategory(oldCategory: String, newCategory: String) {
        val recipes = recipeDao.getAllRecipes().first()
        recipes.forEach { recipe ->
            val updatedCategories = recipe.category.split(", ").joinToString(", ") {
                if (it == oldCategory) newCategory else it
            }

            val updatedRecipe = recipe.copy(category = updatedCategories)
            recipeDao.updateRecipe(updatedRecipe)
        }
    }

    private suspend fun removeCategoryFromRecipes(categoryToRemove: String) {
        val recipes = recipeDao.getAllRecipes().first()
        recipes.forEach { recipe ->
            val updatedCategories = recipe.category.split(", ").filter { it != categoryToRemove }.joinToString(", ")
            val updatedRecipe = recipe.copy(category = updatedCategories)
            recipeDao.updateRecipe(updatedRecipe)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            recipeDao.deleteAllRecipes()
            categoryDao.deleteAllCategories()
            dataStore.setUserLoggedIn(false)
        }
        _firebaseAuth.signOut()
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
    object ThemeChanged : SettingsScreenEvent
}