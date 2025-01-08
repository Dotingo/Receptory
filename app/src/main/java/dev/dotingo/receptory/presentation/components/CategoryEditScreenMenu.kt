package dev.dotingo.receptory.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.ui.icons.ApplyIcon
import dev.dotingo.receptory.ui.icons.CloseIcon
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.arrows.DownArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CategoryEditScreenMenu(
    modifier: Modifier = Modifier,
    selectedItem: String,
    items: Array<String>,
    label: String,
    onItemSelected: (List<Int>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedIndices = remember { mutableStateListOf<Int>() }
    val selectedItems = remember { mutableStateOf(selectedItem) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        ReceptoryInputField(
            value = selectedItems.value,
            onValueChange = {},
            label = label,
            readOnly = true,
            trailingIcon = DownArrowIcon,
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
        )

        if (expanded) {
            ModalBottomSheet(
                modifier = modifier,
                onDismissRequest = { expanded = false }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleIcon(imageVector = CloseIcon) { expanded = false }
                    Text("Категория", style = MaterialTheme.typography.titleLarge)
                    CircleIcon(imageVector = ApplyIcon) {
                        selectedItems.value = selectedIndices.joinToString(", ") { items[it] }
                        onItemSelected(selectedIndices)
                        expanded = false
                    }
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    var searchQuery by remember { mutableStateOf("") }
                    Row {
                        ReceptoryInputField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            label = "Поиск",
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = tinyPadding)
                        )
                        FloatingActionButton(
                            onClick = {},
                            elevation = FloatingActionButtonDefaults.elevation(0.dp)
                        ) {
                            Icon(PlusIcon, "")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn {
                        itemsIndexed(items.filter {
                            it.contains(
                                searchQuery,
                                ignoreCase = true
                            )
                        }) { index, item ->
                            val isSelected = selectedIndices.contains(index)
                            FilterChip(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                selected = isSelected,
                                label = {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        overflow = TextOverflow.Ellipsis,
                                        text = item,
                                        maxLines = 1,
                                        textAlign = TextAlign.Center
                                    )
                                },
                                onClick = {
                                    if (isSelected) {
                                        selectedIndices.remove(index)
                                    } else {
                                        selectedIndices.add(index)
                                    }
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}
