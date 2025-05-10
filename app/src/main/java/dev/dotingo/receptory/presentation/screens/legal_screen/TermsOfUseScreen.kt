package dev.dotingo.receptory.presentation.screens.legal_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfUseScreen(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.term_of_use)) },
                navigationIcon = {
                    CircleIcon(
                        imageVector = BackArrowIcon,
                        modifier = Modifier.padding(start = smallPadding),
                        contentDescription = stringResource(R.string.go_back),
                        onClick = navigateBack
                    )
                }
            )
        }) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                stringResource(R.string.tou_title_1),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_1))
            Text(
                stringResource(R.string.tou_title_2),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_2))
            Text(
                stringResource(R.string.tou_title_3),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_3))
            Text(
                stringResource(R.string.tou_title_4),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_4))
            Text(
                buildAnnotatedString {
                    append("(")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://policies.google.com/privacy",
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                        )
                    ) {
                        append("https://policies.google.com/privacy).\n")
                    }
                },
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                stringResource(R.string.tou_title_5),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_5))
            Text(
                stringResource(R.string.tou_title_6),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_6))
            Text(
                stringResource(R.string.tou_title_7),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.tou_text_7))
        }
    }
}