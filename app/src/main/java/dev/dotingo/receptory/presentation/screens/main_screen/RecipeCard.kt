package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import dev.dotingo.receptory.R
import dev.dotingo.receptory.ui.icons.KcalIcon
import dev.dotingo.receptory.ui.icons.StarIcon
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

@Composable
fun RecipeCard(
    title: String,
    image: String,
    kcal: String,
    category: String,
    isFavorite: Boolean,
    rating: Int,
    onRecipeClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = extraSmallPadding),
        onClick = onRecipeClicked
    ) {
        Row(
            modifier = Modifier.height(recipeCardSize),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image.ifEmpty { R.drawable.recipe_image_placeholder },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
            )
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
                            if (isFavorite) FavoriteBoldIcon else FavoriteOutlinedIcon,
                            "Нравится",
                            tint = favoriteColor
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
                            modifier = Modifier.padding(end = smallPadding).weight(1f)
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