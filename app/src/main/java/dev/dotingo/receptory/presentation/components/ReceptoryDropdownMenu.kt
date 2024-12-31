package dev.dotingo.receptory.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.ui.icons.arrows.DownArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceptoryDropdownMenu(
    modifier: Modifier = Modifier,
    selectedItem: String,
    items: Array<String>,
    label: String,
    shape: Shape = RoundedCornerShape(12.dp),
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
            BasicAlertDialog(
                modifier = modifier,
                onDismissRequest = { expanded = false }
            ) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        items.forEachIndexed { index, item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = item)
                                Spacer(modifier = Modifier.weight(1f))
                                Checkbox(
                                    checked = selectedIndices.contains(index),
                                    onCheckedChange = { isChecked ->
                                        if (isChecked) {
                                            selectedIndices.add(index)
                                        } else {
                                            selectedIndices.remove(index)
                                        }
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        TextButton(
                            onClick = {
                                selectedItems.value = selectedIndices.joinToString(", ") { items[it] }
                                onItemSelected(selectedIndices)
                                expanded = false
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Ок")
                        }
                    }
                }
            }
        }
    }
}
