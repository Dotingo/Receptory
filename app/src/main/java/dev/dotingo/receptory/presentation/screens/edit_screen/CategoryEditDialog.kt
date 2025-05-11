package dev.dotingo.receptory.presentation.screens.edit_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CheckboxRow
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.arrows.FilledArrowDownIcon
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEditDialog(
    modifier: Modifier = Modifier,
    viewModel: EditRecipeViewModel,
    userId: String,
    label: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }

    val selectedCategories = remember(uiState.selectedCategories) {
        uiState.selectedCategories
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()
    }

    var selectedItemsText by remember { mutableStateOf(uiState.selectedCategories) }

    val categoriesListState by viewModel.categories.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        viewModel.loadCategories()
    }
    LaunchedEffect(uiState.selectedCategories) {
        selectedItemsText = uiState.selectedCategories
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        ReceptoryInputField(
            value = selectedItemsText,
            onValueChange = {},
            label = label,
            readOnly = true,
            trailingIcon = FilledArrowDownIcon,
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
        )

        if (expanded) {
            BasicAlertDialog(onDismissRequest = { expanded = false }) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .height(400.dp),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    var searchQuery by remember { mutableStateOf("") }
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(R.string.category),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            ReceptoryInputField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = tinyPadding),
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                label = stringResource(R.string.name),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences
                                )
                            )
                            FloatingActionButton(
                                onClick = {
                                    if (searchQuery.isNotEmpty()) {
                                        viewModel.saveCategory(searchQuery)
                                        searchQuery = ""
                                    }
                                },
                                elevation = FloatingActionButtonDefaults.elevation(0.dp)
                            ) {
                                Icon(PlusIcon, contentDescription = stringResource(R.string.add_category))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            val filteredCategories = categoriesListState.filter {
                                it.name.contains(searchQuery, ignoreCase = true)
                            }
                            items(filteredCategories) { item ->
                                CheckboxRow(
                                    text = item.name,
                                    checked = selectedCategories.contains(item.name.trim()),
                                ) {
                                    viewModel.toggleCategory(item.name.trim())
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(
                            onClick = {
                                expanded = false
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(stringResource(R.string.ok))
                        }
                    }
                }
            }
        }
    }
}
