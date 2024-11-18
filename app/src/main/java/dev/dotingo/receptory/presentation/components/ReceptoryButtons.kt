package dev.dotingo.receptory.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.dotingo.receptory.R
import dev.dotingo.receptory.ui.icons.GoogleLogo
import dev.dotingo.receptory.ui.theme.Dimens.buttonShapeSize
import dev.dotingo.receptory.ui.theme.Dimens.googleSignInButtonSize
import dev.dotingo.receptory.ui.theme.Dimens.largeMediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding

@Composable
fun ReceptoryMainButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(buttonShapeSize)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(smallPadding),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ReceptoryButton(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String, onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(buttonShapeSize)
    ) {
        Text(
            text = text,
            modifier = textModifier,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun GoogleSignInButton(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(googleSignInButtonSize)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = GoogleLogo,
            modifier = Modifier.size(largeMediumIconSize),
            contentDescription = stringResource(R.string.google)
        )
    }
}

@Composable
fun ClickableText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontWeight: FontWeight,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier.clickable(onClick = onClick)
    )
}