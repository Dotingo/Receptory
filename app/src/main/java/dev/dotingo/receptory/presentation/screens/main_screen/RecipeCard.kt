package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.dotingo.receptory.R
import dev.dotingo.receptory.ui.icons.EditIconPadded
import dev.dotingo.receptory.ui.icons.KcalIcon
import dev.dotingo.receptory.ui.icons.PlaceholderIcon
import dev.dotingo.receptory.ui.icons.ShareIconPadded
import dev.dotingo.receptory.ui.icons.StarIcon
import dev.dotingo.receptory.ui.icons.TrashIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteBoldIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteOutlinedIcon
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallIconSize
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.recipeCardSize
import dev.dotingo.receptory.ui.theme.Dimens.smallMediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding
import dev.dotingo.receptory.ui.theme.favoriteColor
import dev.dotingo.receptory.ui.theme.starColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    title: String,
    image: String,
    kcal: String,
    category: String,
    isFavorite: Boolean,
    rating: Int,
    onRecipeClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onFavoriteClicked: () -> Unit
) {
    val haptics = LocalHapticFeedback.current
    var isExpended by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = extraSmallPadding)
            .combinedClickable(
                onClick = { onRecipeClicked() },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    isExpended = true
                }
            ),
    ) {
        DropdownMenu(expanded = isExpended, onDismissRequest = { isExpended = false }) {
            DropdownMenuItem(
                text = { Text("Отправить") },
                leadingIcon = {
                    Icon(
                        imageVector = ShareIconPadded,
                        contentDescription = "Отправить",
                        modifier = Modifier.size(22.dp)
                    )
                },
                onClick = {})
            DropdownMenuItem(
                text = { Text("Редактировать") },
                leadingIcon = {
                    Icon(
                        imageVector = EditIconPadded,
                        contentDescription = "Редактировать",
                        modifier = Modifier.size(22.dp)
                    )
                },
                onClick = {})
            DropdownMenuItem(
                text = { Text("Удалить") },
                leadingIcon = {
                    Icon(
                        imageVector = TrashIcon,
                        contentDescription = "Удалить",
                        modifier = Modifier.size(22.dp)
                    )
                },
                onClick = {
                    onDeleteClicked()
                    isExpended = false
                })
        }
        Row(
            modifier = Modifier.height(recipeCardSize),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image.isNotEmpty()) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                )
            } else {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Icon(
                        PlaceholderIcon,
                        "",
                        modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(smallPadding)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        title,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(modifier = Modifier.size(mediumIconSize),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) FavoriteBoldIcon else FavoriteOutlinedIcon,
                            contentDescription = "Нравится",
                            tint = favoriteColor,
                            modifier = Modifier.clickable {
                                onFavoriteClicked()
                            }
                        )
                    }
                }
                if (rating != 0) {
                    Row {
                        repeat(5) {
                            Icon(
                                StarIcon,
                                "Рейтинг",
                                modifier = Modifier.size(smallMediumIconSize),
                                tint = if (it < rating) {
                                    starColor
                                } else MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                    alpha = 0.5f
                                )
                            )
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (kcal.isNotEmpty()) {
                        Icon(
                            KcalIcon, "Каллории",
                            modifier = Modifier.size(extraSmallIconSize)
                        )
                        Spacer(modifier = Modifier.width(tinyPadding))
                        Text(
                            stringResource(R.string.kcal, kcal),
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(end = smallPadding)
                                .weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (category.isNotEmpty()) {
                        Text(
                            category,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}
