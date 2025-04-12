package dev.dotingo.receptory.presentation.screens.shopping_list_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.local.database.entities.ShoppingItemEntity
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.ui.icons.EditIconPadded
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.TrashIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyBigPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    shoppingListId: Long,
    shoppingListName: String,
    viewModel: ShoppingListViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    LaunchedEffect(shoppingListId) {
        viewModel.setShoppingListId(shoppingListId)
    }
    val shoppingItems by viewModel.shoppingItems.collectAsStateWithLifecycle()
    var newElement by remember {
        mutableStateOf("")
    }
    var isEditListItemDialogExpanded by remember { mutableStateOf(false) }
    var editingListItem by remember { mutableStateOf<ShoppingItemEntity?>(null) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                shoppingListName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
            navigationIcon = {
                CircleIcon(
                    modifier = Modifier.padding(start = smallPadding),
                    imageVector = BackArrowIcon,
                    contentDescription = "Назад"
                ) {
                    navigateBack()
                }
            }
        )
    }) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row {
                ReceptoryInputField(
                    value = newElement,
                    onValueChange = { newElement = it },
                    label = stringResource(R.string.add),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = tinyPadding),
                    keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.addItem(newElement)
                        newElement = ""
                    })
                )
                FloatingActionButton(
                    onClick = {
                        if (newElement.trim().isNotEmpty()) {
                            viewModel.addItem(newElement)
                            newElement = ""
                        }
                    },
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(PlusIcon, "")
                }
            }
            LazyColumn{
                val sortedItems =
                    shoppingItems.sortedWith(compareBy({ it.isPurchased }, { it.itemId }))
                items(sortedItems, key = { it.itemId }) { item ->
                    ShoppingListElement(
                        modifier = Modifier.animateItem(),
                        shoppingItems = item,
                        onItemClick = { viewModel.toggleItemPurchased(item) },
                        onDeleteClick = { viewModel.deleteShoppingItem(item) },
                        onEditClick = {
                            editingListItem = item
                            isEditListItemDialogExpanded = true
                        }
                    )
                }
            }
            if (isEditListItemDialogExpanded && editingListItem != null) {
                BasicAlertDialog(onDismissRequest = {
                    isEditListItemDialogExpanded = false
                    editingListItem = null
                }) {
                    Surface(
                        modifier = Modifier.wrapContentSize(),
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = AlertDialogDefaults.TonalElevation
                    ) {
                        var updatedItemName by remember { mutableStateOf(editingListItem!!.name) }
                        Column(modifier = Modifier.padding(mediumPadding)) {
                            Text(
                                text = "Редактировать элемент",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(mediumPadding))
                            ReceptoryInputField(
                                value = updatedItemName,
                                onValueChange = { updatedItemName = it },
                                label = stringResource(R.string.name),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences
                                )
                            )
                            Spacer(modifier = Modifier.height(mediumPadding))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = {
                                    isEditListItemDialogExpanded = false
                                    editingListItem = null
                                }) {
                                    Text(stringResource(R.string.cancel))
                                }
                                TextButton(onClick = {
                                    if (updatedItemName.trim().isNotEmpty()){
                                        viewModel.updateShoppingItem(editingListItem!!.copy(name = updatedItemName))
                                        isEditListItemDialogExpanded = false
                                        editingListItem = null
                                    }
                                }) {
                                    Text(stringResource(R.string.ok))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListElement(
    modifier: Modifier = Modifier,
    shoppingItems: ShoppingItemEntity,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    var isListToolsExpanded by remember { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.combinedClickable(
            onClick = {
                onItemClick()
            },
            onLongClick = {
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                isListToolsExpanded = true
            }
        )
    ) {
        DropdownMenu(
            expanded = isListToolsExpanded,
            onDismissRequest = { isListToolsExpanded = false }) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.edit)) },
                leadingIcon = {
                    Icon(
                        imageVector = EditIconPadded,
                        contentDescription = stringResource(R.string.edit),
                        modifier = Modifier.size(tinyBigPadding)
                    )
                },
                onClick = {
                    onEditClick()
                    isListToolsExpanded = false
                })
            DropdownMenuItem(
                text = { Text(stringResource(R.string.delete)) },
                leadingIcon = {
                    Icon(
                        imageVector = TrashIcon,
                        contentDescription = stringResource(R.string.delete),
                        modifier = Modifier.size(tinyBigPadding)
                    )
                },
                onClick = {
                    onDeleteClick()
                    isListToolsExpanded = false
                })
        }
        Text(
            shoppingItems.name,
            color = if (shoppingItems.isPurchased) {
                MaterialTheme.colorScheme.onBackground.copy(0.5f)
            } else {
                MaterialTheme.colorScheme.onBackground
            },
            textDecoration = if (shoppingItems.isPurchased) {
                TextDecoration.LineThrough
            } else {
                null
            },
            modifier = Modifier.padding(vertical = mediumPadding)
        )
        HorizontalDivider()
    }
}