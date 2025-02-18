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
import androidx.compose.runtime.toMutableStateList
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
    // Получаем состояние из ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }

    // Преобразуем строку выбранных категорий в список
    val selectedCategories = uiState.selectedCategories
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .toMutableStateList()

    // Текстовое поле для отображения выбранных категорий
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
                                Icon(PlusIcon, contentDescription = "Добавить категорию")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            val filteredCategories = categoriesListState.filter {
                                it.name.contains(searchQuery, ignoreCase = true)
                            }
                            items(filteredCategories) { item ->
                                // Проверяем, выбрана ли категория, используя логику из ViewModel
                                val isSelected = selectedCategories.contains(item.name)
                                CheckboxRow(text = item.name, checked = isSelected) {
                                    // Переключаем состояние выбранной категории через ViewModel
                                    viewModel.toggleCategory(item.name)
                                    // Обновляем локальный список для отображения (UI обновится при новом состоянии)
                                    if (isSelected) {
                                        selectedCategories.remove(item.name)
                                    } else {
                                        selectedCategories.add(item.name)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(
                            onClick = {
                                selectedItemsText = selectedCategories.joinToString(", ")
                                viewModel.setSelectedCategories(selectedCategories)
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

//
//private fun loadCategories(
//    firestore: FirebaseFirestore,
//    userId: String,
//    onCategories: (List<Category>) -> Unit
//) {
//    firestore.collection("categories")
//        .whereEqualTo("userId", userId).get()
//        .addOnSuccessListener { result ->
//            onCategories(result.toObjects(Category::class.java))
//        }
//        .addOnFailureListener {
//            Log.d("MyLog", "${it.message}")
//        }
//}
//
//private fun saveCategoryToFirestore(
//    firestore: FirebaseFirestore,
//    category: Category,
//    onSaved: () -> Unit,
//    onError: () -> Unit
//) {
//    firestore.collection("categories")
//        .whereEqualTo("userId", category.userId)
//        .whereEqualTo("name", category.name)
//        .get()
//        .addOnSuccessListener { result ->
//            if (result.isEmpty) {
//                val db = firestore.collection("categories")
//                val key = db.document().id
//                db.document(key)
//                    .set(category)
//                    .addOnSuccessListener {
//                        onSaved()
//                    }.addOnFailureListener {
//                        Log.d("MyLog", it.message.toString())
//                        onError()
//                    }
//            }
//        }
//        .addOnFailureListener {
//            Log.d("MyLog", it.message.toString())
//            onError()
//        }
//}