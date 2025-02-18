package dev.dotingo.receptory.presentation.screens.settings_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.RadioButtonRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleSelectDialog(
    modifier: Modifier,
    title: String,
    optionsList: List<String>,
    defaultSelected: Int,
    submitButtonText: String,
    dismissButtonText: String,
    onSubmitButtonClick: (Int) -> Unit,
    onDismissRequest: (Boolean) -> Unit
) {

    var selectedOption by remember {
        mutableIntStateOf(defaultSelected)
    }

    BasicAlertDialog(onDismissRequest = { onDismissRequest(false) }) {
        Surface(
            modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = modifier.padding(16.dp)) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(optionsList) {
                        RadioButtonRow(
                            text = it,
                            selectedOption = optionsList[selectedOption],
                            onOptionSelected = { selectedValue ->
                                selectedOption = optionsList.indexOf(selectedValue)
                                onSubmitButtonClick.invoke(selectedOption)
                                onDismissRequest.invoke(false)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        onDismissRequest.invoke(false)
                    }
                ) { Text(stringResource(R.string.ok)) }
            }
        }
    }
}

