package dev.dotingo.receptory.presentation.screens.shopping_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.data.local.database.dao.ShoppingItemDao
import dev.dotingo.receptory.data.local.database.dao.ShoppingListDao
import dev.dotingo.receptory.data.local.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.data.local.database.entities.ShoppingListEntity
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
    private val shoppingListDao: ShoppingListDao,
    private val shoppingItemDao: ShoppingItemDao
) : ViewModel() {
    val shoppingLists = shoppingListDao.getAllShoppingListsWithItemsFlow().flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addShoppingList(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListDao.insert(ShoppingListEntity(name = name))
        }
    }

    fun deleteShoppingList(shoppingList: ShoppingListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListDao.delete(shoppingList)
        }
    }

    fun updateShoppingList(shoppingList: ShoppingListEntity){
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListDao.update(shoppingList)
        }
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemDao.delete(shoppingItem)
        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItemEntity){
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemDao.update(shoppingItem)
        }
    }

    private val _shoppingListId = MutableStateFlow<Long?>(null)
    fun setShoppingListId(id: Long){
        _shoppingListId.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val shoppingItems: StateFlow<List<ShoppingItemEntity>> = _shoppingListId
        .filterNotNull()
        .flatMapLatest { id ->
            shoppingItemDao.getItemsForShoppingListFlow(id)
                .flowOn(Dispatchers.IO)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Добавляем новый элемент в список
    fun addItem(name: String) {
        val listId = _shoppingListId.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemDao.insert(
                ShoppingItemEntity(shoppingListId = listId, name = name, isPurchased = false)
            )
        }
    }

    // Переключаем статус покупки элемента
    fun toggleItemPurchased(item: ShoppingItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            shoppingItemDao.update(item.copy(isPurchased = !item.isPurchased))
        }
    }

}