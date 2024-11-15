package dev.dotingo.receptory.presentation.screens.shopping_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.ui.icons.MoreVertIcon
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    var newElement by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                "Один из списков покупок с типа длинным названием",
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
            },
            actions = {
                CircleIcon(
                    modifier = Modifier.padding(end = smallPadding),
                    imageVector = MoreVertIcon,
                    iconColor = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Ещё"
                ) {

                }
            }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = commonHorizontalPadding)
        ) {
            Row {
                ReceptoryInputField(
                    value = newElement,
                    onValueChange = { newElement = it },
                    label = "Добавить",
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

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(10) {
                    ShoppingListElement()
                }
            }
        }
    }
}

@Composable
fun ShoppingListElement() {
    var isSelected by remember {
        mutableStateOf(false)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { isSelected = !isSelected }
    ) {
        Text(
            LoremIpsum().values.first().take(100),
            color = if (isSelected) {
                MaterialTheme.colorScheme.onBackground.copy(0.5f)
            } else {
                MaterialTheme.colorScheme.onBackground
            },
            textDecoration = if (isSelected) {
                TextDecoration.LineThrough
            }else{
                null
            },
            modifier = Modifier.padding(vertical = 15.dp)
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun ShoppingListScreenPreview() {
    ReceptoryTheme {
        ShoppingListScreen() {

        }
    }
}