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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.local.database.entities.ShoppingListEntity
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.ui.icons.EditIconPadded
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.ShareIconPadded
import dev.dotingo.receptory.ui.icons.TrashIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyBigPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsMenuScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToShoppingList: (Long, String) -> Unit,
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val shoppingLists by viewModel.shoppingLists.collectAsStateWithLifecycle()
    var isCreateListDialogExpanded by remember { mutableStateOf(false) }
    var isEditListDialogExpanded by remember { mutableStateOf(false) }
    var editingList by remember { mutableStateOf<ShoppingListEntity?>(null) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.shopping_lists_title)) },
                navigationIcon = {
                    CircleIcon(
                        modifier = Modifier.padding(start = smallPadding),
                        imageVector = BackArrowIcon,
                        contentDescription = stringResource(R.string.go_back)
                    ) {
                        navigateBack()
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(shape = CircleShape, onClick = {
                isCreateListDialogExpanded = true
            }) {
                Icon(PlusIcon, stringResource(R.string.create_shopping_list))
            }
        }
    ) { paddingValues ->
        if (isCreateListDialogExpanded) {
            BasicAlertDialog(onDismissRequest = { isCreateListDialogExpanded = false }) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    var shoppingListName by remember { mutableStateOf("") }
                    Column(modifier = Modifier.padding(mediumPadding)) {
                        Text(
                            text = stringResource(R.string.shopping_list_name),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(mediumPadding))
                        ReceptoryInputField(
                            modifier = Modifier.padding(end = tinyPadding),
                            value = shoppingListName,
                            onValueChange = { shoppingListName = it },
                            label = stringResource(R.string.name),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences
                            )
                        )
                        Spacer(modifier = Modifier.height(mediumPadding))
                        TextButton(
                            onClick = {
                                if (shoppingListName.trim().isNotEmpty()) {
                                    viewModel.addShoppingList(shoppingListName)
                                    isCreateListDialogExpanded = false
                                }
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(stringResource(R.string.ok))
                        }
                    }
                }
            }
        }

        if (isEditListDialogExpanded && editingList != null) {
            BasicAlertDialog(onDismissRequest = { isEditListDialogExpanded = false }) {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    var shoppingListName by remember { mutableStateOf(editingList!!.name) }
                    Column(modifier = Modifier.padding(mediumPadding)) {
                        Text(
                            text = stringResource(R.string.edit_name),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(mediumPadding))
                        ReceptoryInputField(
                            value = shoppingListName,
                            onValueChange = { shoppingListName = it },
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
                            TextButton(onClick = { isEditListDialogExpanded = false }) {
                                Text(stringResource(R.string.cancel))
                            }
                            TextButton(onClick = {
                                if (shoppingListName.trim().isNotEmpty()) {
                                    viewModel.updateShoppingList(
                                        editingList!!.copy(name = shoppingListName)
                                    )
                                    isEditListDialogExpanded = false
                                }
                            }) {
                                Text(stringResource(R.string.ok))
                            }
                        }
                    }
                }
            }
        }
        LazyColumn(
            contentPadding = paddingValues, modifier = modifier.fillMaxSize()
        ) {
            items(
                shoppingLists,
                key = { shoppingList -> shoppingList.shoppingList.shoppingListId }) { listWithItems ->
                ShoppingListCard(
                    modifier = Modifier.animateItem(),
                    onClick = {
                        navigateToShoppingList(
                            listWithItems.shoppingList.shoppingListId,
                            listWithItems.shoppingList.name
                        )
                    },
                    onDeleteClick = { viewModel.deleteShoppingList(listWithItems.shoppingList) },
                    onEditClick = {
                        editingList = listWithItems.shoppingList
                        isEditListDialogExpanded = true
                    },
                    onShareClick = {
                        viewModel.shareShoppingList(
                            context,
                            listWithItems.shoppingList,
                            listWithItems.items
                        )
                    },
                    name = listWithItems.shoppingList.name,
                    purchasedCount = listWithItems.items.count { it.isPurchased },
                    listSize = listWithItems.items.size
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onShareClick: () -> Unit,
    name: String,
    purchasedCount: Int,
    listSize: Int
) {
    var isListToolsExpanded by remember { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    onClick()
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
                text = { Text(stringResource(R.string.share)) },
                leadingIcon = {
                    Icon(
                        imageVector = ShareIconPadded,
                        contentDescription = stringResource(R.string.share),
                        modifier = Modifier.size(tinyBigPadding)
                    )
                },
                onClick = { onShareClick() })
            DropdownMenuItem(
                text = { Text(stringResource(R.string.rename)) },
                leadingIcon = {
                    Icon(
                        imageVector = EditIconPadded,
                        contentDescription = stringResource(R.string.rename),
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
        Row(modifier = Modifier.padding(smallPadding)) {
            Text(
                name,
                modifier = Modifier.weight(1f)
            )
            Text("$purchasedCount/${listSize}")
        }
    }
    Spacer(Modifier.height(smallPadding))
}