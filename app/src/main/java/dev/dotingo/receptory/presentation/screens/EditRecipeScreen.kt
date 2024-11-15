package dev.dotingo.receptory.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryButton
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.presentation.components.ReceptoryLargeInputField
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.icons.CloseIcon
import dev.dotingo.receptory.ui.icons.RecipePlaceholder
import dev.dotingo.receptory.ui.icons.StarIcon
import dev.dotingo.receptory.ui.icons.WebIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteBoldIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteOutlinedIcon
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraBigIconSize
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme
import dev.dotingo.receptory.ui.theme.favoriteColor
import dev.dotingo.receptory.ui.theme.starColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRecipeScreen(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var cookingTime by rememberSaveable { mutableStateOf("") }
    var portions by rememberSaveable { mutableStateOf("") }
    var kcal by rememberSaveable { mutableStateOf("") }
    var ingredients by rememberSaveable { mutableStateOf("") }
    var recipe by rememberSaveable { mutableStateOf("") }
    var siteLink by rememberSaveable { mutableStateOf("") }
    var videoLink by rememberSaveable { mutableStateOf("") }
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    var isImageVisible by rememberSaveable { mutableStateOf(false) }
    var rating by remember { mutableStateOf(0) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(R.string.add_recipe)) },
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
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = WebIcon,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = stringResource(R.string.add_interner_recipe)
                    )
                }
            })
    }, bottomBar = {
        ReceptoryMainButton(
            modifier = Modifier
                .padding(horizontal = commonHorizontalPadding)
                .padding(top = smallPadding)
                .navigationBarsPadding(),
            text = stringResource(R.string.add)
        ) {

        }
    }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = commonHorizontalPadding)
                .verticalScroll(rememberScrollState())
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            Row(Modifier.fillMaxWidth()) {
                ReceptoryInputField(
                    modifier = Modifier.weight(1f),
                    value = title,
                    onValueChange = { title = it },
                    label = stringResource(R.string.recipe_name),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.width(smallPadding))
                Box(
                    modifier = Modifier
                        .height(TextFieldDefaults.MinHeight)
                        .aspectRatio(1f)
                        .border(
                            width = 2.dp,
                            color = favoriteColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            isFavorite = !isFavorite
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isFavorite) FavoriteBoldIcon else FavoriteOutlinedIcon,
                        "Нравится",
                        tint = favoriteColor
                    )
                }
            }
            Spacer(Modifier.height(mediumPadding))
            if (isImageVisible) {
                Image(
                    imageVector = RecipePlaceholder,
                    contentDescription = "Изображение рецепта",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(mediumPadding))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                ReceptoryButton(
                    text = stringResource(R.string.add_image)
                ) { isImageVisible = true }
                if (isImageVisible) {
                    IconButton(onClick = { isImageVisible = false }) {
                        Icon(
                            CloseIcon, stringResource(R.string.clear_image),
                            modifier.padding(start = extraSmallPadding)
                        )
                    }
                }
            }
            Spacer(Modifier.height(mediumPadding))
            RatingBar(
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                }
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryLargeInputField(
                value = description,
                onValueChange = { description = it },
                label = stringResource(R.string.recipe_description)
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryInputField(
                value = cookingTime,
                onValueChange = { cookingTime = it },
                label = stringResource(R.string.cooking_time)
            )
            Spacer(Modifier.height(mediumPadding))
            Row {
                ReceptoryInputField(
                    modifier = Modifier.weight(1f),
                    value = portions,
                    onValueChange = { portions = it },
                    label = stringResource(R.string.portions),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.width(mediumPadding))
                ReceptoryInputField(
                    modifier = Modifier.weight(1f),
                    value = kcal,
                    onValueChange = { kcal = it },
                    label = stringResource(R.string.calories),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(Modifier.height(mediumPadding))
            ReceptoryLargeInputField(
                value = ingredients,
                onValueChange = { ingredients = it },
                label = stringResource(R.string.ingredients)
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(stringResource(R.string.help))
                    }
                    append(stringResource(R.string.ingredients_help))
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = extraSmallPadding)
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryLargeInputField(
                value = recipe,
                onValueChange = { recipe = it },
                label = stringResource(R.string.cooking_recipe)
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(stringResource(R.string.help))
                    }
                    append(stringResource(R.string.cooking_help))
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = extraSmallPadding)
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryInputField(
                value = siteLink,
                onValueChange = { siteLink = it },
                label = stringResource(R.string.site_link),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryInputField(
                value = videoLink,
                onValueChange = { videoLink = it },
                label = stringResource(R.string.video_link),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    var currentRating by remember { mutableIntStateOf(rating) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = modifier.pointerInput(Unit) {
            detectDragGestures { change, _ ->
                val position = change.position.x
                val newRating = (position / extraBigIconSize.toPx()).toInt() + 1
                currentRating = newRating.coerceIn(1, 5)
                onRatingChanged(currentRating)
            }
        }) {
            val interactionSource = remember { MutableInteractionSource() }
            repeat(5) { index ->
                Icon(
                    imageVector = StarIcon,
                    contentDescription = "Рейтинг $index",
                    modifier = Modifier
                        .size(extraBigIconSize)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            if ((rating - 1) == index) {
                                onRatingChanged(0)
                            } else {
                                onRatingChanged(index + 1)
                            }
                        },
                    tint = if (index < rating) {
                        starColor
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun EditRecipeScreenPreview() {
    ReceptoryTheme {
        EditRecipeScreen() {

        }
    }
}