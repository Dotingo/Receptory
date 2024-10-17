package dev.dotingo.receptory.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.onSurfaceDark

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navigateToOnboardingScreen: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.welcome_background),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(
                    color = Color.Black.copy(alpha = 0.6f),
                    blendMode = BlendMode.Darken
                )
            )
            .systemBarsPadding()
            .padding(horizontal = commonHorizontalPadding)
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Column(modifier = Modifier.weight(0.7f)) {
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.displaySmall,
                color = onSurfaceDark
            )
            Text(
                text = stringResource(R.string.to_receptory),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = onSurfaceDark
            )
        }
        ReceptoryMainButton(
            text = stringResource(R.string.start_now_button)
        ) {
            navigateToOnboardingScreen()
        }
    }
}