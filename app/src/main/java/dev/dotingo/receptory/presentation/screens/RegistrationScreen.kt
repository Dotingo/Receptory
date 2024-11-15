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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.nulabinc.zxcvbn.Zxcvbn
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.AuthHeader
import dev.dotingo.receptory.presentation.components.ClickableText
import dev.dotingo.receptory.presentation.components.GoogleSignInButton
import dev.dotingo.receptory.presentation.components.OrDivider
import dev.dotingo.receptory.presentation.components.ReceptoryInputField
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.presentation.components.ReceptoryPasswordInputField
import dev.dotingo.receptory.presentation.components.SwitchAuthModeText
import dev.dotingo.receptory.ui.icons.EmailIcon
import dev.dotingo.receptory.ui.icons.UserIcon
import dev.dotingo.receptory.ui.theme.Dimens.bigPadding
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
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
    var passwordError by rememberSaveable { mutableStateOf(false) }

    val zxcvbn = Zxcvbn()
    val passwordStrength = zxcvbn.measure(password)
    fun validatePasswords() {
        passwordError = password.length < 8 || !password.isPasswordValid() || passwordStrength.score < 3
    }
    fun getPasswordSupportingText(): String? {
        return when {
            password.isEmpty() -> null
            password.length < 8 -> "Пароль должен содержать 8-16 символов и состоять из цифр, прописных и строчных латинских букв"
            !password.isPasswordValid() -> "Пароль должен содержать 8-16 символов и состоять из цифр, прописных и строчных латинских букв"
            passwordStrength.score < 3 -> "Пароль слишком простой"
            else -> null
        }
    }

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
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryInputField(
            value = username,
            onValueChange = { username = it },
            label = stringResource(R.string.tf_name),
            icon = UserIcon,
            keyboardOptions = KeyboardOptions(
                capitalization =  KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryInputField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.tf_email),
            icon = EmailIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryPasswordInputField(
            password = password,
            onPasswordChange = {
                password = it
                passwordError = false
                validatePasswords()
            },
            label = stringResource(R.string.password),
            passwordVisibility = passwordVisibility,
            onVisibilityChange = { passwordVisibility = !passwordVisibility },
            isError = passwordError,
            supportingText = getPasswordSupportingText()
        )
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryMainButton(
            text = stringResource(R.string.registration_bt)
        ) {
            validatePasswords()
            if (!passwordError) {

            }
        }

        Spacer(modifier = Modifier.height(bigPadding))
        ClickableText(
            modifier = Modifier.alpha(0.5f),
            text = stringResource(R.string.continue_without_reg),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        ) {
            navigateToMainScreen()
        }
        Spacer(modifier = Modifier.height(bigPadding))
        OrDivider()
        Spacer(modifier = Modifier.height(bigPadding))
        GoogleSignInButton()
        Spacer(modifier = Modifier.weight(1f))
        SwitchAuthModeText(stringResource(R.string.have_account), stringResource(R.string.login)) {
            navigateToLoginScreen()
        }
    }
}

fun String.isPasswordValid(): Boolean {
    return Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@\$!%*?&]{8,}\$")
        .matches(this)
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    ReceptoryTheme {
        RegistrationScreen(navigateToMainScreen = {}, navigateToLoginScreen = {})
    }
}