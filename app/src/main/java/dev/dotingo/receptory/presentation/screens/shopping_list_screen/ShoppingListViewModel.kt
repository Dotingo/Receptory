package dev.dotingo.receptory.presentation.screens.shopping_list_screen

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.database.entities.ShoppingListEntity
import dev.dotingo.receptory.domain.repository.ShoppingItemRepository
import dev.dotingo.receptory.domain.repository.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val shoppingItemRepository: ShoppingItemRepository
) : ViewModel() {
    val shoppingLists = shoppingListRepository.getAllShoppingListsWithItemsFlow().flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addShoppingList(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListRepository.insertShoppingList(ShoppingListEntity(name = name))
        }
    }

    fun deleteShoppingList(shoppingList: ShoppingListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListRepository.deleteShoppingList(shoppingList)
        }
    }

    fun updateShoppingList(shoppingList: ShoppingListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListRepository.updateShoppingList(shoppingList)
        }
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemRepository.deleteShoppingItem(shoppingItem)
        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemRepository.updateShoppingItem(shoppingItem)
        }
    }

    private val _shoppingListId = MutableStateFlow<Long?>(null)
    fun setShoppingListId(id: Long) {
        _shoppingListId.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val shoppingItems: StateFlow<List<ShoppingItemEntity>> = _shoppingListId
        .filterNotNull()
        .flatMapLatest { id ->
            shoppingItemRepository.getItemsForShoppingListFlow(id)
                .flowOn(Dispatchers.IO)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addItem(name: String) {
        val listId = _shoppingListId.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemRepository.insertShoppingItem(
                ShoppingItemEntity(shoppingListId = listId, name = name, isPurchased = false)
            )
        }
    }

    fun toggleItemPurchased(item: ShoppingItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemRepository.updateShoppingItem(item.copy(isPurchased = !item.isPurchased))
        }
    }

    fun shareShoppingList(context: Context, shoppingList: ShoppingListEntity, items: List<ShoppingItemEntity>) {
        val resources = context.resources
        val nameLabel = resources.getString(R.string.name)

        val shopText = buildString {
            appendLine("$nameLabel: ${shoppingList.name}")
            appendLine()
            items.forEachIndexed { index, item ->
                val status = if (item.isPurchased) "✔" else "✖"
                appendLine("$status ${index + 1}. ${item.name}")
            }
        }

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shopText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share list")
        context.startActivity(shareIntent)
    }
}