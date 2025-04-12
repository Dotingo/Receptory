package dev.dotingo.receptory.presentation.screens.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.AuthHeader
import dev.dotingo.receptory.presentation.components.GoogleSignInButton
import dev.dotingo.receptory.presentation.components.OrDivider
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.presentation.components.ReceptoryPasswordInputField
import dev.dotingo.receptory.presentation.components.SwitchAuthModeText
import dev.dotingo.receptory.ui.icons.EmailIcon
import dev.dotingo.receptory.ui.theme.Dimens.bigPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel = hiltViewModel(),
    navigateToRegistrationScreen: () -> Unit,
    navigateToMainScreen: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            stringResource(R.string.login_title),
            stringResource(R.string.welcome_back)
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryInputField(
            value = state.email,
            onValueChange = viewModel::updateEmail,
            label = stringResource(R.string.tf_email),
            leadingIcon = EmailIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryPasswordInputField(
            password = state.password,
            onPasswordChange = viewModel::updatePassword,
            label = stringResource(R.string.password),
            isError = false,
            passwordVisibility = state.passwordVisibility,
            onVisibilityChange = viewModel::togglePasswordVisibility
        )
        Text(
            text = "Забыли пароль?",
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = tinyPadding)
                .clickable {
                    viewModel.resetPassword()
                },
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryMainButton(
            text = stringResource(R.string.login_bt)
        ) {
            if (state.email.isNotEmpty() && state.password.isNotEmpty()) {
                viewModel.signIn(onSuccessful = navigateToMainScreen)
            }
        }
        Text(
            text = state.authErrorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = smallPadding),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(bigPadding))
        Spacer(modifier = Modifier.height(bigPadding))
        OrDivider()
        Spacer(modifier = Modifier.height(bigPadding))
        GoogleSignInButton()
        Spacer(modifier = Modifier.weight(1f))
        SwitchAuthModeText(stringResource(R.string.no_account), stringResource(R.string.register)) {
            navigateToRegistrationScreen()
        }
    }
}
