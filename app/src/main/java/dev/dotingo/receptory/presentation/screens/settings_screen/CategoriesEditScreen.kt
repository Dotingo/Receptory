package dev.dotingo.receptory.presentation.screens.settings_screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.local.database.entities.CategoryEntity
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.ui.icons.EditIcon
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.TrashIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallMediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesEditScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    var isCreateCategoryDialogExpanded by remember { mutableStateOf(false) }
    var isEditCategoryDialogExpanded by remember { mutableStateOf(false) }
    var editingCategory by remember { mutableStateOf<CategoryEntity?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.category)) },
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
                isCreateCategoryDialogExpanded = true
            }) {
                Icon(PlusIcon, stringResource(R.string.category))
            }
        }
    ) { paddingValues ->
        if (isCreateCategoryDialogExpanded) {
            CategoryDialog(
                title = stringResource(R.string.category),
                initialName = "",
                onConfirm = { categoryName ->
                    viewModel.addCategory(categoryName)
                    isCreateCategoryDialogExpanded = false
                },
                onDismiss = { isCreateCategoryDialogExpanded = false }
            )
        }

        if (isEditCategoryDialogExpanded && editingCategory != null) {
            CategoryDialog(
                title = stringResource(R.string.category),
                initialName = editingCategory!!.name,
                onConfirm = { categoryName ->
                    editingCategory?.let { viewModel.updateCategory(it, categoryName) }
                    isEditCategoryDialogExpanded = false
                },
                onDismiss = { isEditCategoryDialogExpanded = false }
            )
        }

        LazyColumn(
            contentPadding = paddingValues, modifier = modifier.fillMaxSize()
        ) {
            items(categories, key = { it.categoryId }) { category ->
                CategoryItem(
                    category = category,
                    onEditClick = {
                        editingCategory = category
                        isEditCategoryDialogExpanded = true
                    },
                    onDeleteClick = { viewModel.deleteCategory(category) }
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: CategoryEntity,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(smallPadding)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = smallPadding, vertical = extraSmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.name,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onEditClick) {
                Icon(EditIcon, contentDescription = stringResource(R.string.edit_category), modifier = Modifier.size(smallMediumIconSize))
            }
            IconButton(onClick = onDeleteClick) {
                Icon(TrashIcon, contentDescription = stringResource(R.string.delete_category))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDialog(
    title: String,
    initialName: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var categoryName by remember { mutableStateOf(initialName) }

    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(mediumPadding)) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(mediumPadding))
                ReceptoryInputField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    label = stringResource(R.string.name),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )
                Spacer(modifier = Modifier.height(mediumPadding))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(stringResource(R.string.cancel))
                    }
                    TextButton(onClick = { if (categoryName.trim().isNotEmpty()) onConfirm(categoryName) }) {
                        Text(stringResource(R.string.ok))
                    }
                }
            }
        }
    }
}
