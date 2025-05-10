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
fun PrivacyPolicyScreen(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.privacy_policy)) },
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
            Text(stringResource(R.string.privacy_policy_desc))
            Text(
                stringResource(R.string.pp_title_1),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_1))
            Text(
                stringResource(R.string.pp_title_2),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_2))
            Text(
                stringResource(R.string.pp_title_3),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_3))
            Text(
                buildAnnotatedString {
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://policies.google.com/privacy",
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                        )
                    ) {
                        append(stringResource(R.string.google_pp))
                    }
                },
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                buildAnnotatedString {
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://firebase.google.com/support/privacy?hl=ru",
                            styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                        )
                    ) {
                        append(stringResource(R.string.firebase_pp))
                    }
                },
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                stringResource(R.string.pp_title_4),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_4))
            Text(
                stringResource(R.string.pp_title_5),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_5))
            Text(
                stringResource(R.string.pp_title_6),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_6))
            Text(
                stringResource(R.string.pp_title_7),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(stringResource(R.string.pp_text_7))
        }
    }
}
