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
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.database.entities.RecipeEntity
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListEntity
import dev.dotingo.receptory.domain.repository.RecipeRepository
import dev.dotingo.receptory.domain.repository.ShoppingItemRepository
import dev.dotingo.receptory.domain.repository.ShoppingListRepository
import dev.dotingo.receptory.presentation.screens.main_screen.appendIfNotBlank
import dev.dotingo.receptory.utils.getLocalizedContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RecipeScreenViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val shoppingListRepository: ShoppingListRepository,
    private val shoppingItemRepository: ShoppingItemRepository,
) : ViewModel() {

    var recipe by mutableStateOf(RecipeEntity())
        private set

    fun fetchRecipe(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            recipe = recipeRepository.getRecipeByKey(key) ?: RecipeEntity()
        }
    }

    fun toggleFavorite() {
        val newFavoriteValue = !recipe.favorite
        recipe = recipe.copy(favorite = newFavoriteValue)
        viewModelScope.launch {
            recipeRepository.updateRecipe(recipe)
        }
    }

    fun shareRecipe(recipe: RecipeEntity, context: Context) {
        val languageCode = Locale.getDefault().language
        val localizedContext = getLocalizedContext(context, languageCode)
        val recipeText = buildString {
            appendIfNotBlank("${localizedContext.getString(R.string.name)}: ", "\"${recipe.title}\"")
            appendIfNotBlank("${localizedContext.getString(R.string.recipe_description)}:\n", recipe.description)
            appendIfNotBlank("${localizedContext.getString(R.string.category)}: ", recipe.category)
            appendIfNotBlank("${localizedContext.getString(R.string.cooking_time)}: ", recipe.cookingTime)
            appendIfNotBlank("${localizedContext.getString(R.string.portions)}: ", recipe.portions)
            appendIfNotBlank("${localizedContext.getString(R.string.calories)}: ", recipe.kcal)

            if (recipe.ingredients.isNotBlank()) {
                append("\n🛒 ${localizedContext.getString(R.string.ingredients)}:\n")
                recipe.ingredients.lines()
                    .filter { it.isNotBlank() }
                    .forEach { append("- $it\n") }
            }

            if (recipe.cookingSteps.isNotBlank()) {
                append("\n👨‍🍳 ${localizedContext.getString(R.string.cooking_steps)}:\n")
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
            val listId = shoppingListRepository.insertShoppingList(ShoppingListEntity(name = name))
            val items = ingredients.map { ShoppingItemEntity(shoppingListId = listId.toLong(), name = it) }
            shoppingItemRepository.insertAllShoppingItems(items)
        }
    }
}