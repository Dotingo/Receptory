package dev.dotingo.receptory.presentation.screens.edit_screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryButton
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.presentation.components.ReceptoryLargeInputField
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.icons.CloseIcon
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
import dev.dotingo.receptory.ui.theme.favoriteColor
import dev.dotingo.receptory.ui.theme.starColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRecipeScreen(
    modifier: Modifier = Modifier,
    key: String,
    viewModel: EditRecipeViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsStateWithLifecycle()
    val userId by viewModel.uid.collectAsStateWithLifecycle()

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            viewModel.onImageSelected(uri)
        }
    }

    LaunchedEffect(key) {
        viewModel.initialize(key)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    if (key.isEmpty()) stringResource(R.string.add_recipe) else stringResource(
                        R.string.edit_recipe
                    )
                )
            },
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
                if (key.isEmpty()) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = WebIcon,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(R.string.add_interner_recipe)
                        )
                    }
                }
            })
    }, bottomBar = {
        ReceptoryMainButton(
            modifier = Modifier
                .padding(top = smallPadding)
                .padding(horizontal = commonHorizontalPadding)
                .navigationBarsPadding(),
            text = if (key.isEmpty()) stringResource(R.string.add) else stringResource(R.string.edit),
            enabled = isButtonEnabled
        ) {
            if (uiState.title.isNotEmpty()) {
                viewModel.saveRecipe(key, onSaved = {
                    navigateBack()
                },
                    onError = {})
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.enter_recipe_name), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            Row(Modifier.fillMaxWidth()) {
                ReceptoryInputField(
                    modifier = Modifier.weight(1f),
                    value = uiState.title,
                    onValueChange = { viewModel.onFieldChange(EditRecipeField.TITLE, it) },
                    label = stringResource(R.string.name),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
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
                            viewModel.onFavoriteChange()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (uiState.isFavorite) FavoriteBoldIcon else FavoriteOutlinedIcon,
                        contentDescription = stringResource(R.string.like),
                        tint = favoriteColor
                    )
                }
            }
            Spacer(Modifier.height(mediumPadding))
            if (uiState.selectedImageUri != null) {
                AsyncImage(
                    model = uiState.selectedImageUri,
                    contentDescription = stringResource(R.string.recipe_image),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Log.d("MyLog", "EditRecipeScreen: ${uiState.selectedImageUri}")
                Spacer(Modifier.height(mediumPadding))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                ReceptoryButton(
                    text = if (uiState.selectedImageUri == null) stringResource(R.string.add_image) else stringResource(
                        R.string.edit_image
                    )
                ) {
                    imageLauncher.launch("image/*")
                }
                if (uiState.selectedImageUri.toString().isEmpty()) viewModel.onImageSelected(null)
                if (uiState.selectedImageUri != null) {
                    IconButton(onClick = { viewModel.onImageSelected(null) }) {
                        Icon(
                            imageVector = CloseIcon,
                            contentDescription = stringResource(R.string.remove_image),
                            modifier = Modifier.padding(start = extraSmallPadding)
                        )
                    }
                }
            }
            Spacer(Modifier.height(mediumPadding))
            RatingBar(
                rating = uiState.rating,
                onRatingChanged = { newRating ->
                    viewModel.onRatingChange(newRating)
                }
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryLargeInputField(
                value = uiState.description,
                onValueChange = { viewModel.onFieldChange(EditRecipeField.DESCRIPTION, it) },
                label = stringResource(R.string.recipe_description),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Spacer(Modifier.height(mediumPadding))
            CategoryEditDialog(
                modifier = Modifier.fillMaxWidth(),
                userId = userId,
                viewModel = viewModel,
                label = stringResource(R.string.category)
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryInputField(
                value = uiState.cookingTime,
                onValueChange = { viewModel.onFieldChange(EditRecipeField.COOKING_TIME, it) },
                label = stringResource(R.string.cooking_time),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(mediumPadding))
            Row {
                ReceptoryInputField(
                    modifier = Modifier.weight(1f),
                    value = uiState.portions,
                    onValueChange = { input ->
                        viewModel.onFieldChange(
                            EditRecipeField.PORTIONS,
                            input.filter { it in "0123456789" })
                    },
                    label = stringResource(R.string.portions),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.width(mediumPadding))
                ReceptoryInputField(
                    modifier = Modifier.weight(1f),
                    value = uiState.kcal,
                    onValueChange = { input ->
                        val filteredInput = input.filter { it in "0123456789" }
                        if (filteredInput.length <= 5) {
                            viewModel.onFieldChange(EditRecipeField.KCAL, filteredInput)
                        }
                    },
                    label = stringResource(R.string.calories),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(Modifier.height(mediumPadding))
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
            ReceptoryLargeInputField(
                value = uiState.ingredients,
                onValueChange = { viewModel.onFieldChange(EditRecipeField.INGREDIENTS, it) },
                label = stringResource(R.string.ingredients),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Spacer(Modifier.height(mediumPadding))
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
            ReceptoryLargeInputField(
                value = uiState.cookingSteps,
                onValueChange = { viewModel.onFieldChange(EditRecipeField.COOKING_STEPS, it) },
                label = stringResource(R.string.cooking_steps),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryInputField(
                value = uiState.siteLink,
                onValueChange = { viewModel.onFieldChange(EditRecipeField.SITE_LINK, it) },
                label = stringResource(R.string.site_link),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(mediumPadding))
            ReceptoryInputField(
                value = uiState.videoLink,
                onValueChange = { viewModel.onFieldChange(EditRecipeField.VIDEO_LINK, it) },
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
                    contentDescription = stringResource(R.string.rating, index),
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
