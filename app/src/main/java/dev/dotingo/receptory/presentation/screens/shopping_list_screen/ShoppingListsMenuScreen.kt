package dev.dotingo.receptory.presentation.screens.shopping_list_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsMenuScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToShoppingList: () -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(stringResource(R.string.shopping_list_title)) },
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
    },
        floatingActionButton = {
            FloatingActionButton(shape = CircleShape, onClick = {}) {
                Icon(PlusIcon, "Добавить лист покупок")
            }
        }
    ) {
        LazyColumn(
            contentPadding = it, modifier = modifier.fillMaxSize()
        ) {
            items(50) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = commonHorizontalPadding)
                        .fillMaxWidth(),
                    onClick = {
                        navigateToShoppingList()
                    }
                ) {
                    Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                        Text(
                            LoremIpsum().values.first().take(5 * it),
                            modifier = Modifier.weight(1f)
                        )
                        Text("1/6")
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingListsMenuScreenPreview() {
    ReceptoryTheme {
        ShoppingListsMenuScreen(navigateBack = {}) {

        }
    }
}