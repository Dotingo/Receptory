package dev.dotingo.receptory.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.AuthHeader
import dev.dotingo.receptory.presentation.components.ClickableText
import dev.dotingo.receptory.presentation.components.GoogleSignInButton
import dev.dotingo.receptory.presentation.components.OrDivider
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.presentation.components.ReceptoryPasswordInputField
import dev.dotingo.receptory.presentation.components.SwitchAuthModeText
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumPadding
import dev.dotingo.receptory.ui.icons.EmailIcon
import dev.dotingo.receptory.ui.icons.UserIcon
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navigateToLoginScreen: () -> Unit,
    navigateToMainScreen: () -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
            .padding(horizontal = commonHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            stringResource(R.string.registration_title),
            stringResource(R.string.create_first_account)
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        ReceptoryInputField(
            value = username,
            onValueChange = { username = it },
            placeholder = stringResource(R.string.tf_name),
            icon = UserIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        ReceptoryInputField(
            value = email,
            onValueChange = { email = it },
            placeholder = stringResource(R.string.tf_email),
            icon = EmailIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        ReceptoryPasswordInputField(
            password = password,
            onPasswordChange = { password = it },
            passwordVisibility = passwordVisibility,
            onVisibilityChange = { passwordVisibility = !passwordVisibility }
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        ReceptoryMainButton(
            text = stringResource(R.string.registration_bt)
        ) {

        }
        Spacer(modifier = Modifier.height(mediumPadding))
        ClickableText(text = stringResource(R.string.continue_without_reg)) {
            navigateToMainScreen()
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        OrDivider()
        Spacer(modifier = Modifier.height(mediumPadding))
        GoogleSignInButton()
        Spacer(modifier = Modifier.weight(1f))
        SwitchAuthModeText(stringResource(R.string.have_account), stringResource(R.string.login)) {
            navigateToLoginScreen()
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    ReceptoryTheme {
        RegistrationScreen(navigateToMainScreen = {}, navigateToLoginScreen = {})
    }
}