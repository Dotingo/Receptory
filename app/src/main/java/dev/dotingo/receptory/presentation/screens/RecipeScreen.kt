package dev.dotingo.receptory.presentation.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.sp
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ClickableText
import dev.dotingo.receptory.ui.icons.EditIcon
import dev.dotingo.receptory.ui.icons.RecipePlaceholder
import dev.dotingo.receptory.ui.icons.ShareIcon
import dev.dotingo.receptory.ui.icons.ShoppingListIcon
import dev.dotingo.receptory.ui.icons.StarIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.icons.favorite.FavoriteOutlinedIcon
import dev.dotingo.receptory.ui.theme.Dimens.bigIconSize
import dev.dotingo.receptory.ui.theme.Dimens.bigImageSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme
import dev.dotingo.receptory.ui.theme.onBackgroundDark
import dev.dotingo.receptory.ui.theme.starColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    val listState = rememberLazyListState()
    val firstVisibleIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex }
    }
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = if (firstVisibleIndex > 0) Color.Unspecified else Color.Transparent),
            title = {},
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
                    imageVector = FavoriteOutlinedIcon,
                    contentDescription = "Нравится",
                    iconSize = bigIconSize
                ) {

                }
                Spacer(Modifier.width(extraSmallPadding))
                CircleIcon(
                    imageVector = ShareIcon,
                    contentDescription = "Поделиться"
                ) {

                }
                Spacer(Modifier.width(extraSmallPadding))
                CircleIcon(
                    modifier = Modifier.padding(end = smallPadding),
                    imageVector = EditIcon,
                    contentDescription = "Редактировать"
                ) {

                }
            })
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { RecipeHeader() }

            item { RecipeDescription() }

            item { RecipeInfo() }

            item { IngredientsSection() }

            item { CookingStepsSection() }

            item {
                LinkSection(
                    stringResource(R.string.site_link),
                    "https://www.russianfood.com/recipes/recipe.php?rid=164495"
                )
            }

            item {
                LinkSection(
                    stringResource(R.string.video_link),
                    "https://www.youtube.com/watch?v=RbFth1Dm6D8"
                )
            }
        }
    }
}

@Composable
private fun RecipeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .height(bigImageSize)
    ) {
        Image(
            RecipePlaceholder,
            "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = 1200f
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
                    LoremIpsum().values.first().take(50),
                    color = onBackgroundDark,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.basicMarquee(repeatDelayMillis = 2_000)

                )
                Text(
                    LoremIpsum().values.first().take(10),
                    color = onBackgroundDark,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.basicMarquee()
                )
            }
            Spacer(Modifier.width(smallPadding))
            Row(modifier = Modifier.align(Alignment.Bottom)) {
                repeat(5) {
                    Icon(
                        StarIcon,
                        "Рейтинг",
                        modifier = Modifier.size(bigIconSize),
                        tint = starColor
                    )
                }
            }
        }
    }
}


@Composable
private fun RecipeDescription() {
    Spacer(Modifier.padding(top = smallPadding))
    Column(
        modifier = Modifier
            .padding(horizontal = commonHorizontalPadding)
    ) {
        Text(
            stringResource(R.string.recipe_description),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(smallPadding))
        Text(
            LoremIpsum().values.first().take(500),
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider(
            Modifier
                .padding(vertical = smallPadding)
                .background(MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Composable
private fun RecipeInfo() {
    Column(
        modifier = Modifier
            .padding(horizontal = commonHorizontalPadding)
    ) {
        Text(
            stringResource(R.string.general_information),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(smallPadding))
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append(stringResource(R.string.cooking_time))
            }
            append("1 час 20 минут")
        }, style = MaterialTheme.typography.bodyLarge)
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append("${stringResource(R.string.portions)}: ")
            }
            append("8 порций")
        }, style = MaterialTheme.typography.bodyLarge)
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append("${stringResource(R.string.calories)}: ")
            }
            append("1000 ккал")
        }, style = MaterialTheme.typography.bodyLarge)
        HorizontalDivider(
            Modifier
                .padding(vertical = smallPadding)
                .background(MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Composable
private fun IngredientsSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = commonHorizontalPadding)
    ) {
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

            }
        }
        Spacer(Modifier.height(smallPadding))
        Text(
            "Яблоки - 3 небольших (300 г)\n" +
                    "Яйца C0 (крупные) - 4 шт.\n" +
                    "Сахар - 200 г (можно 100-120 г)\n" +
                    "Мука - 120 г (4-5 ст. ложек)\n" +
                    "Сок лимона - 2 ст. ложки\n" +
                    "Сахарная пудра (по желанию) - 0,5 ст. ложки",
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider(
            Modifier
                .padding(vertical = smallPadding)
                .background(MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Composable
private fun CookingStepsSection() {
    val cookingSteps = listOf(
        LoremIpsum().values.first().take(50),
        LoremIpsum().values.first().take(100),
        LoremIpsum().values.first().take(250)
    )
    Column(
        modifier = Modifier
            .padding(horizontal = commonHorizontalPadding)
    ) {
        Text(
            stringResource(R.string.cooking_recipe),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        repeat(cookingSteps.size) {
            Spacer(Modifier.height(smallPadding))
            Text(
                stringResource(R.string.stage, it + 1),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                cookingSteps[it],
                style = MaterialTheme.typography.bodyLarge
            )
        }

        HorizontalDivider(
            Modifier
                .padding(vertical = smallPadding)
                .background(MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Composable
private fun LinkSection(title: String, url: String) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }
    Column(
        modifier = Modifier
            .padding(horizontal = commonHorizontalPadding)
    ) {
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

@Preview(showBackground = true)
@Composable
private fun RecipeScreenPreview() {
    ReceptoryTheme {
        RecipeScreen() {

        }
    }
}