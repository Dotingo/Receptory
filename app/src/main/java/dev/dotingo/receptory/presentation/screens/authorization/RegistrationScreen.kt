package dev.dotingo.receptory.presentation.screens.authorization

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import dev.dotingo.receptory.ui.theme.Dimens.bigPadding
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit,
    navigateToMainScreen: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val verificationDialog by viewModel.verificationDialog.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
            .padding(horizontal = commonHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (verificationDialog) {
            VerificationDialog(
                navigateToMainScreen = navigateToMainScreen,
                viewModel = viewModel
            )
        }

        AuthHeader(
            stringResource(R.string.registration_title),
            stringResource(R.string.create_first_account)
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
            passwordVisibility = state.passwordVisibility,
            onVisibilityChange = viewModel::togglePasswordVisibility,
        )
        if (state.passwordError.isNotEmpty()) {
            Text(
                text = state.passwordError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = smallPadding),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(bigPadding))
        ReceptoryMainButton(
            text = stringResource(R.string.registration_bt),
        ) {
            viewModel.signUp()
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
        ClickableText(
            modifier = Modifier.alpha(0.5f),
            text = stringResource(R.string.continue_without_reg),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        ) {
            viewModel.signOut()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationDialog(
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit,
    viewModel: AuthorizationViewModel
) {
    val context = LocalContext.current
    viewModel.monitorEmailVerification {
        Toast.makeText(context, context.getString(R.string.email_confirmed), Toast.LENGTH_SHORT).show()
        navigateToMainScreen()
    }
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = {}
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.waiting_email_confirmation),
                )
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = { viewModel.closeVerificationDialog() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}
