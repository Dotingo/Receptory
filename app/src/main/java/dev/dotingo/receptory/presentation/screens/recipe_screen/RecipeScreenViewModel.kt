package dev.dotingo.receptory.presentation.screens.recipe_screen

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.database.dao.RecipeDao
import dev.dotingo.receptory.data.local.database.dao.ShoppingItemDao
import dev.dotingo.receptory.data.local.database.dao.ShoppingListDao
import dev.dotingo.receptory.data.local.database.entities.RecipeEntity
import dev.dotingo.receptory.data.local.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.local.database.entities.ShoppingListEntity
import dev.dotingo.receptory.utils.appendIfNotBlank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RecipeScreenViewModel @Inject constructor(
    private val recipeDao: RecipeDao,
    private val shoppingListDao: ShoppingListDao,
    private val shoppingItemDao: ShoppingItemDao
) : ViewModel() {

    var recipe by mutableStateOf(RecipeEntity())
        private set

    fun fetchRecipe(key: String) {
        viewModelScope.launch {
            recipe = recipeDao.getRecipeByKey(key) ?: recipe
        }
    }

    fun toggleFavorite() {
        val newFavoriteValue = !recipe.favorite
        recipe = recipe.copy(favorite = newFavoriteValue)
        viewModelScope.launch {
            recipeDao.updateRecipe(recipe)
        }
    }

    fun shareRecipe(recipe: RecipeEntity, context: Context) {
        val recipeText = buildString {
            appendIfNotBlank("Название: ", "\"${recipe.title}\"")
            appendIfNotBlank("Описание:\n", recipe.description)
            appendIfNotBlank("Категория: ", recipe.category)
            appendIfNotBlank("Время приготовления: ", recipe.cookingTime)
            appendIfNotBlank("Порции: ", recipe.portions)
            appendIfNotBlank("Калории: ", recipe.kcal)

            if (recipe.ingredients.isNotBlank()) {
                append("\n🛒 Ингредиенты:\n")
                recipe.ingredients.lines()
                    .filter { it.isNotBlank() }
                    .forEach { append("- $it\n") }
            }

            if (recipe.cookingSteps.isNotBlank()) {
                append("\n👨‍🍳 Шаги приготовления:\n")
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
        context.startActivity(Intent.createChooser(shareIntent, "Поделиться рецептом"))
    }

    fun createShoppingListWithItems(name: String, ingredients: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val listId = shoppingListDao.insert(ShoppingListEntity(name = name))
            val items = ingredients.map { ShoppingItemEntity(shoppingListId = listId.toLong(), name = it) }
            shoppingItemDao.insertAll(items)
        }
    }
}