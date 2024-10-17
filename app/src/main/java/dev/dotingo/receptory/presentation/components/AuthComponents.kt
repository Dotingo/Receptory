package dev.dotingo.receptory.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.dotingo.receptory.R
import dev.dotingo.receptory.ui.theme.Dimens.smallMediumImageSize
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.icons.AppIcon

@Composable
fun AuthHeader(title: String, description: String) {
    Image(
        imageVector = AppIcon,
        modifier = Modifier
            .size(smallMediumImageSize)
            .padding(vertical = mediumPadding),
        contentDescription = null
    )
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = description,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.5f)
    )
}

@Composable
fun SwitchAuthModeText(
    text: String, clickableText: String,
    onClick: () -> Unit
) {
    Row {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = clickableText,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { onClick() }
        )
    }
}

@Composable
fun OrDivider() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            modifier = Modifier.weight(0.4f),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.or),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = smallPadding)
        )
        HorizontalDivider(
            modifier = Modifier.weight(0.4f),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}