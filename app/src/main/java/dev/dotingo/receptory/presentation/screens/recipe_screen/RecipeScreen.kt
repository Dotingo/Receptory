package dev.dotingo.receptory.presentation.screens.recipe_screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.ui.icons.EditIcon
import dev.dotingo.receptory.ui.icons.PlaceholderIcon
import dev.dotingo.receptory.ui.icons.ShareIcon
import dev.dotingo.receptory.ui.icons.ShoppingListIcon
import dev.dotingo.receptory.ui.icons.StarIcon
import dev.dotingo.receptory.ui.icons.TimerIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteBoldIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteOutlinedIcon
import dev.dotingo.receptory.ui.theme.Dimens.bigImageSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.onBackgroundDark
import dev.dotingo.receptory.ui.theme.starColor
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    key: String,
    navigateBack: () -> Unit,
    navigateToShoppingListMenuScreen: () -> Unit,
    navigateToEditRecipeScreen: (String) -> Unit,
    navigateToTimerScreen: () -> Unit,
    viewModel: RecipeScreenViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val firstVisibleIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val recipeState = viewModel.recipe
    val context = LocalContext.current
    LaunchedEffect(key) {
        viewModel.fetchRecipe(key)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (firstVisibleIndex > 0) Color.Unspecified
                    else Color.Transparent
                ),
                title = {},
                navigationIcon = {
                    CircleIcon(
                        modifier = Modifier.padding(start = smallPadding),
                        imageVector = BackArrowIcon,
                        contentDescription = stringResource(R.string.go_back)
                    ) {
                        navigateBack()
                    }
                },
                actions = {
                    CircleIcon(
                        imageVector = if (recipeState.favorite) FavoriteBoldIcon else FavoriteOutlinedIcon,
                        contentDescription = stringResource(R.string.like),
                        iconSize = mediumIconSize
                    ) {
                        viewModel.toggleFavorite()
                    }
                    Spacer(Modifier.width(extraSmallPadding))
                    CircleIcon(
                        imageVector = ShareIcon,
                        contentDescription = stringResource(R.string.share)
                    ) {
                        viewModel.shareRecipe(recipeState, context)
                    }
                    Spacer(Modifier.width(extraSmallPadding))
                    CircleIcon(
                        modifier = Modifier.padding(end = smallPadding),
                        imageVector = EditIcon,
                        contentDescription = stringResource(R.string.share)
                    ) {
                        navigateToEditRecipeScreen(key)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RecipeHeader(
                    image = recipeState.imageUrl,
                    title = recipeState.title,
                    category = recipeState.category,
                    rating = recipeState.rating
                )
            }

            item { RecipeDescription(description = recipeState.description) }

            item {
                RecipeInfo(
                    cookingTime = recipeState.cookingTime,
                    portions = recipeState.portions,
                    kcal = recipeState.kcal
                )
            }

            item {
                IngredientsSection(
                    ingredients = recipeState.ingredients,
                    onCreateShoppingListClick = {
                        viewModel.createShoppingListWithItems(
                            context.getString(R.string.shopping_list_for, recipeState.title),
                            it
                        )
                    }
                ) {
                    navigateToShoppingListMenuScreen()
                }
            }

            item {
                CookingStepsSection(
                    cookingSteps = recipeState.cookingSteps,
                    navigateToTimerScreen = navigateToTimerScreen
                )
            }

            item {
                LinkSection(
                    title = stringResource(R.string.site_link),
                    url = recipeState.websiteUrl
                )
            }

            item {
                LinkSection(
                    title = stringResource(R.string.video_link),
                    url = recipeState.videoUrl
                )
            }
        }
    }
}

@Composable
private fun RecipeHeader(image: String, title: String, category: String, rating: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .height(bigImageSize)
    ) {
        if (image.isNotEmpty()) {
            AsyncImage(
                File(image),
                "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Icon(
                    PlaceholderIcon,
                    "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = 2000f
                    )
                )
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(smallPadding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    color = onBackgroundDark,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.basicMarquee(repeatDelayMillis = 2_000)
                )
                if (category.isNotEmpty()) {
                    Text(
                        category,
                        color = onBackgroundDark,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                        modifier = Modifier.basicMarquee()
                    )
                }
            }
            Spacer(Modifier.width(smallPadding))
            Row(modifier = Modifier.align(Alignment.Bottom)) {
                repeat(rating) {
                    Icon(
                        StarIcon,
                        stringResource(R.string.rating),
                        modifier = Modifier.size(mediumIconSize),
                        tint = starColor
                    )
                }
            }
        }
    }
}


@Composable
private fun RecipeDescription(description: String) {
    if (description.isNotEmpty()) {
        Spacer(Modifier.padding(top = smallPadding))
        Column(modifier = Modifier.padding(horizontal = commonHorizontalPadding)) {
            Text(
                stringResource(R.string.recipe_description),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(smallPadding))
            Text(
                description,
                style = MaterialTheme.typography.bodyLarge
            )
            HorizontalDivider(
                Modifier
                    .padding(vertical = smallPadding)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }

}

@Composable
private fun RecipeInfo(cookingTime: String, portions: String, kcal: String) {
    if (cookingTime.isNotEmpty() || portions.isNotEmpty() || kcal.isNotEmpty()) {
        val infoList = listOf(
            stringResource(R.string.cooking_time) to cookingTime,
            stringResource(R.string.portions) to portions,
            stringResource(R.string.calories) to kcal
        )
        Column(modifier = Modifier.padding(horizontal = commonHorizontalPadding)) {
            Text(
                stringResource(R.string.general_information),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(smallPadding))
            infoList.filter { it.second.isNotEmpty() }.forEach { (label, value) ->
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("$label: ")
                    }
                    append(value)
                }, style = MaterialTheme.typography.bodyLarge)
            }
            HorizontalDivider(
                Modifier
                    .padding(vertical = smallPadding)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IngredientsSection(
    ingredients: String,
    onCreateShoppingListClick: (List<String>) -> Unit,
    navigateToShoppingListMenuScreen: () -> Unit
) {
    val shoppingList = remember { mutableStateListOf<String>() }
    val ingredientSelection = remember { mutableStateMapOf<String, Boolean>() }

    LaunchedEffect(ingredients) {
        shoppingList.clear()
        ingredientSelection.clear()
        ingredients.lines().filter { it.isNotBlank() }.forEach { ingredient ->
            shoppingList.add(ingredient)
            ingredientSelection[ingredient] = false // По умолчанию все в списке
        }
    }

    if (ingredients.isNotEmpty()) {
        Column(modifier = Modifier.padding(horizontal = commonHorizontalPadding)) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.ingredients),
                    style = MaterialTheme.typography.headlineSmall
                )
                CircleIcon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    imageVector = ShoppingListIcon,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    iconColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    onCreateShoppingListClick(shoppingList)
                    navigateToShoppingListMenuScreen()
                }
            }
            Spacer(Modifier.height(smallPadding))
            ingredients.lines().forEach { ingredient ->
                val isSelected = ingredientSelection[ingredient] == true

                Text(
                    ingredient,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onBackground.copy(0.5f)
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                    modifier = Modifier.clickable {
                        val newSelected = !isSelected
                        ingredientSelection[ingredient] = newSelected
                        if (newSelected) {
                            shoppingList.remove(ingredient)
                        } else {
                            shoppingList.add(ingredient)
                        }
                    }
                )
            }
            Text(
                text = stringResource(R.string.ingredient_hint),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = mediumPadding)
            )
            HorizontalDivider(
                Modifier
                    .padding(vertical = smallPadding)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Composable
private fun CookingStepsSection(
    cookingSteps: String,
    navigateToTimerScreen: () -> Unit
) {
    if (cookingSteps.isNotEmpty()) {
        Column(modifier = Modifier.padding(horizontal = commonHorizontalPadding)) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.cooking_steps),
                    style = MaterialTheme.typography.headlineSmall
                )
                CircleIcon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    imageVector = TimerIcon,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    iconColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    navigateToTimerScreen()
                }
            }
            Spacer(Modifier.height(smallPadding))

            cookingSteps.lines().forEachIndexed { index, step ->
                var isSelected by rememberSaveable { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isSelected = !isSelected
                        }) {
                    Text(
                        stringResource(R.string.stage, index + 1),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.secondary.copy(0.5f)
                        } else {
                            MaterialTheme.colorScheme.secondary
                        }
                    )
                    Text(
                        step,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.onBackground.copy(0.5f)
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        },
                        modifier = Modifier
                            .padding(extraSmallPadding)
                    )
                }
            }
            Text(
                text = stringResource(R.string.cook_steps_hint),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = mediumPadding)
            )
            HorizontalDivider(
                Modifier
                    .padding(vertical = smallPadding)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Composable
private fun LinkSection(title: String, url: String) {
    if (url.isNotEmpty()) {
        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW, url.toUri()) }
        Column(modifier = Modifier.padding(horizontal = commonHorizontalPadding)) {
            Text(
                title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        context.startActivity(intent)
                    }
            )
            Spacer(Modifier.height(smallPadding))
            Text(
                buildAnnotatedString {
                    withLink(
                        LinkAnnotation.Url(
                            url = url,
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                        )
                    ) {
                        append(url)
                    }
                },
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary
            )
            HorizontalDivider(
                Modifier
                    .padding(vertical = smallPadding)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}