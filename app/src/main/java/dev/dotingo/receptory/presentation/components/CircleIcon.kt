package dev.dotingo.receptory.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import dev.dotingo.receptory.ui.theme.Dimens.circleIconContainerSize
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize

@Composable
fun CircleIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
    iconSize: Dp = mediumIconSize,
    iconColor: Color = LocalContentColor.current,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(circleIconContainerSize)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            modifier = Modifier.size(iconSize),
            contentDescription = contentDescription,
            tint = iconColor
        )
    }
}