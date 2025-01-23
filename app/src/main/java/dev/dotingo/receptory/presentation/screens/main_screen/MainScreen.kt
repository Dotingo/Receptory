package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.dotingo.receptory.R
import dev.dotingo.receptory.data.Recipe
import dev.dotingo.receptory.presentation.components.RadioButtonRow
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.icons.SettingsIcon
import dev.dotingo.receptory.ui.icons.arrows.FilledArrowDownIcon
import dev.dotingo.receptory.ui.icons.arrows.FilledArrowUpIcon
import dev.dotingo.receptory.ui.icons.arrows.OutlinedArrowDownIcon
import dev.dotingo.receptory.ui.icons.arrows.OutlinedArrowUpIcon
import dev.dotingo.receptory.ui.theme.Dimens.bigImageSize
import dev.dotingo.receptory.ui.theme.Dimens.bigPadding
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallMediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel(),
    navigateToRecipeScreen: (String) -> Unit,
    navigateToEditRecipeScreen: () -> Unit,
    navigateToShoppingListMenuScreen: () -> Unit,
    navigateToTimerScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit
) {
    val recipesListState by viewModel.recipesListState.collectAsStateWithLifecycle()
    val isFavFilter by viewModel.isFavoriteFilterOn.collectAsStateWithLifecycle()
    val isSortFilterOpen by viewModel.isSortFilterOpen.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchAllRecipes()
    }
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
                        imageVector = FilledArrowDownIcon,
                        contentDescription = "Выбрать категорию",
                        modifier = Modifier.size(smallMediumIconSize)
                    )
                }
            },
                actions = {
                    IconButton(
                        onClick = {
                            navigateToSettingsScreen()
                        }
                    ) {
                        Icon(
                            imageVector = SettingsIcon,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }

                }
            )
        }, bottomBar = {
            ReceptoryBottomBar(
                onAddRecipeButtonClick = {
                    navigateToEditRecipeScreen()
                },
                onShoppingListClick = {
                    navigateToShoppingListMenuScreen()
                },
                onTimerClick = {
                    navigateToTimerScreen()
                }
            )
        }
    ) { innerPadding ->
        if (recipesListState.isEmpty()) {
            EmptyMenuScreen(
                modifier = modifier,
                onEditRecipeClick = { navigateToEditRecipeScreen() },
                innerPadding = innerPadding
            )
        } else {
            RecipeContent(
                modifier = modifier,
                innerPadding = innerPadding,
                recipesListState = recipesListState,
                isFavFilter = isFavFilter,
                isSortFilterOpen = isSortFilterOpen,
                navigateToRecipeScreen = navigateToRecipeScreen,
                onDeleteClicked = { key ->
                    viewModel.deleteRecipe(key)
                },
                onFavoriteClicked = { key, isLiked ->
                    viewModel.changeLike(key, isLiked)
                },
                onSortFilterClicked = {
                    viewModel.changeSortFilter(it)
                },
                onFavoriteFilterClicked = {
                    viewModel.changeFavFilter(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeContent(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    recipesListState: List<Recipe>,
    isFavFilter: Boolean,
    isSortFilterOpen: Boolean,
    navigateToRecipeScreen: (String) -> Unit,
    onDeleteClicked: (key: String) -> Unit,
    onFavoriteClicked: (key: String, isLiked: Boolean) -> Unit,
    onSortFilterClicked: (Boolean) -> Unit,
    onFavoriteFilterClicked: (Boolean) -> Unit
) {
    val filters = listOf(
        stringResource(R.string.by_name_sort),
        stringResource(R.string.by_rating_sort),
        stringResource(R.string.by_calories_sort),
        stringResource(R.string.by_changes_sort)
    )
    var selectedFilter by remember { mutableIntStateOf(3) }
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = commonHorizontalPadding)
            .padding(innerPadding)
    ) {
        RecipeSearchBar(
            text = searchText,
            onTextChange = { searchText = it },
            placeholder = stringResource(R.string.search_placeholder),
            onClearClicked = { searchText = "" },
            onSortFilterClicked = { isOpen ->
                onSortFilterClicked(isOpen)
            },
            onFavoriteFilterClicked = { isFavorite ->
                onFavoriteFilterClicked(isFavorite)
            }
        )
        if (isSortFilterOpen) {
            BasicAlertDialog(onDismissRequest = {
                onSortFilterClicked(false)
            }) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            stringResource(R.string.sorting),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        repeat(filters.size) {
                            RadioButtonRow(
                                checked = selectedFilter == it,
                                text = filters[it],
                                onCheckedChange = { selectedFilter = it })
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        var down by remember { mutableStateOf(false) }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(modifier = Modifier.clickable { down = !down }) {
                                Icon(
                                    imageVector = if (down) FilledArrowDownIcon else OutlinedArrowDownIcon,
                                    contentDescription = ""
                                )
                                Icon(
                                    imageVector = if (down) OutlinedArrowUpIcon else FilledArrowUpIcon,
                                    contentDescription = ""
                                )
                            }
                            TextButton(
                                onClick = { }
                            ) { Text(stringResource(R.string.ok)) }
                        }

                    }
                }
            }
        }
        LazyColumn {
            val filteredCategories = recipesListState.filter {
                it.title.contains(searchText, ignoreCase = true) &&
                        (!isFavFilter || it.favorite)
            }
            items(filteredCategories,
                key = { it.key }) { recipe ->
                RecipeCard(
                    modifier = Modifier.animateItem(),
                    title = recipe.title,
                    image = recipe.imageUrl,
                    kcal = recipe.kcal,
                    category = recipe.category,
                    isFavorite = recipe.favorite,
                    rating = recipe.rating,
                    onRecipeClicked = {
                        navigateToRecipeScreen(recipe.key)
                    },
                    onDeleteClicked = { onDeleteClicked(recipe.key) },
                    onFavoriteClicked = { onFavoriteClicked(recipe.key, recipe.favorite) }
                )
            }
            item {
                Spacer(Modifier.height(smallPadding))
            }
        }
    }
}

@Composable
private fun EmptyMenuScreen(
    modifier: Modifier = Modifier,
    onEditRecipeClick: () -> Unit,
    innerPadding: PaddingValues
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(horizontal = commonHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_screen_book))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            modifier = Modifier
                .size(bigImageSize),
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
            text = stringResource(R.string.add_recipe)
        ) {
            onEditRecipeClick()
        }
    }
}
