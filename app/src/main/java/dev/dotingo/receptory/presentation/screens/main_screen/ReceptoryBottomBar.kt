package dev.dotingo.receptory.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.R
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.icons.PlusIcon
import dev.dotingo.receptory.ui.icons.ShoppingListIcon
import dev.dotingo.receptory.ui.icons.TimerIcon

@Composable
fun ReceptoryBottomBar(
    onAddRecipeButtonClick: () -> Unit,
    onShoppingListClick: () -> Unit,
    onTimerClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BottomAppBarDefaults.containerColor)
                .navigationBarsPadding()
                .padding(horizontal = commonHorizontalPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = ShoppingListIcon,
                text = stringResource(R.string.shopping_list),
                onClick = onShoppingListClick
            )
            BottomBarItem(
                icon = TimerIcon,
                text = stringResource(R.string.timer),
                onClick = onTimerClick
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-28).dp),
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            onClick = {
                onAddRecipeButtonClick()
            }
        ) {
            Icon(PlusIcon, contentDescription = "Добавить рецепт")
        }
    }
}

@Composable
fun BottomBarItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(
                icon, contentDescription = text,
                modifier = Modifier.size(mediumIconSize)
            )
        }
        Text(text)
    }
}