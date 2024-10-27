package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.presentation.components.RecipeSearchBar
import dev.dotingo.receptory.ui.icons.RecipePlaceholder
import dev.dotingo.receptory.ui.icons.arrows.DownArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.bigImageSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.bigPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToRecipeScreen: () -> Unit,
    navigateToAddRecipeScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {

                    }
                ) {
                    Text(
                        text = stringResource(R.string.all_category),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = extraSmallPadding)
                    )
                    Icon(
                        imageVector = DownArrowIcon,
                        contentDescription = "Выбрать категорию",
                        modifier = Modifier.size(mediumIconSize)
                    )
                }

            })
        }, bottomBar = {
            ReceptoryBottomBar(
                onAddRecipeButtonClick = {
                    navigateToAddRecipeScreen()
                },
                onShoppingListClick = {},
                onSettingsClick = {}
            )
        }
    ) { innerPadding ->
//        EmptyMenuScreen(
        //        onAddRecipeClicked = {},
        //        innerPadding = innerPadding
        //        )

        RecipeContent(innerPadding, navigateToRecipeScreen)
    }
}

@Composable
fun RecipeContent(innerPadding: PaddingValues, navigateToRecipeScreen:() -> Unit) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = commonHorizontalPadding)
            .padding(innerPadding)
    ) {
        RecipeSearchBar(
            text = searchText,
            onTextChange = { searchText = it },
            placeholder = stringResource(R.string.search_placeholder),
            onClearClicked = { searchText = "" },
            onFilterClicked = {},
            onFavoriteClicked = {}
        )
        LazyColumn {
            items(10) {
                RecipeCard(
                    title = "рецепт печени",
                    image = RecipePlaceholder,
                    kcal = "890",
                    category = "Завтрак",
                    isFavorite = false,
                    rating = 5
                ) {
                    navigateToRecipeScreen()
                }
            }
            item {
                Spacer(Modifier.height(smallPadding))
            }
        }
    }
}

@Composable
private fun EmptyMenuScreen(
    onAddRecipeClicked: () -> Unit,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(horizontal = commonHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_screen_book))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            modifier = Modifier.size(bigImageSize),
            composition = composition,
            progress = { progress },
        )
        Text(
            stringResource(R.string.empty_recipe_list),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(smallPadding))
        Text(
            stringResource(R.string.adding_now),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryMainButton(
            modifier = Modifier.fillMaxWidth(),
            textModifier = Modifier.padding(vertical = 10.dp),
            text = stringResource(R.string.add_recipe)
        ) {
            onAddRecipeClicked()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    ReceptoryTheme {
        MainScreen(navigateToRecipeScreen = {}){}
    }
}