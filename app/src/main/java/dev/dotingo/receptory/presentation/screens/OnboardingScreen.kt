package dev.dotingo.receptory.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.bigPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.icons.CookingIcons
import dev.dotingo.receptory.ui.icons.FolderIcon
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.SearchIcon
import dev.dotingo.receptory.ui.icons.ShoppingListIcon
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navigateToRegistrationScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(R.string.app_capabilities_title),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            })
        }, bottomBar = {
            ReceptoryMainButton(
                modifier = Modifier
                    .padding(horizontal = commonHorizontalPadding)
                    .padding(top = smallPadding)
                    .navigationBarsPadding(),
                text = stringResource(R.string.understood_button)
            ) {
                navigateToRegistrationScreen()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = commonHorizontalPadding)
                .verticalScroll(rememberScrollState())
        ) {
            InfoTextWithLabel(
                title = stringResource(R.string.info_title_recipe),
                description = stringResource(R.string.info_description_recipe),
                icon = PlusIcon
            )
            InfoTextWithLabel(
                title = stringResource(R.string.info_title_cooking),
                description = stringResource(R.string.info_description_cooking),
                icon = CookingIcons
            )
            InfoTextWithLabel(
                title = stringResource(R.string.info_title_shoppinglist),
                description = stringResource(R.string.info_description_shoppinglist),
                icon = ShoppingListIcon
            )
            InfoTextWithLabel(
                title = stringResource(R.string.info_title_search),
                description = stringResource(R.string.info_description_search),
                icon = SearchIcon
            )
            InfoTextWithLabel(
                title = stringResource(R.string.info_title_storage),
                description = stringResource(R.string.info_description_storage),
                icon = FolderIcon
            )
        }
    }
}

@Composable
fun InfoTextWithLabel(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = bigPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon, contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(mediumIconSize)
        )
        Spacer(modifier = Modifier.width(smallPadding))
        Column {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = description,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    ReceptoryTheme {
        OnboardingScreen { }
    }
}