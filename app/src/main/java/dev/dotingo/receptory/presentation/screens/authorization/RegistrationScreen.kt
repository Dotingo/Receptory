package dev.dotingo.receptory.presentation.screens.authorization

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Suppress("DEPRECATION")
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit,
    navigateToMainScreen: () -> Unit,
    navigateToTermOfUseScreen: () -> Unit,
    navigateToPrivacyPolicyScreen: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val verificationDialog by viewModel.verificationDialog.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)

    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { event ->
            when (event) {
                is AuthorizationViewModel.NavEvent.ToMain -> navigateToMainScreen()
                is AuthorizationViewModel.NavEvent.ToLogin -> navigateToLoginScreen()
            }
        }
    }
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = viewModel::onGoogleSignInResult,
        onAuthError = { e ->
            Toast.makeText(context,
                context.getString(R.string.google_sign_in_error), Toast.LENGTH_SHORT).show()
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (verificationDialog) {
            VerificationDialog(
                email = state.email,
                closeDialogClick = { viewModel.closeVerificationDialog() },
                monitorEmailVerification = {
                    viewModel.monitorEmailVerification {
                        Toast.makeText(
                            context,
                            context.getString(R.string.email_confirmed),
                            Toast.LENGTH_SHORT
                        ).show()
                        navigateToMainScreen()
                    }
                }
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
            viewModel.registerOrLinkByEmail({}){
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        Spacer(modifier = Modifier.height(extraSmallPadding))
        AgreementText(
            onTermsClick = { navigateToTermOfUseScreen() },
            onPrivacyClick = { navigateToPrivacyPolicyScreen() })
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
        GoogleSignInButton {
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        }
        Spacer(modifier = Modifier.weight(1f))
        SwitchAuthModeText(stringResource(R.string.have_account), stringResource(R.string.login)) {
            navigateToLoginScreen()
        }
    }
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                    .clickable(enabled = false) {},
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationDialog(
    modifier: Modifier = Modifier,
    email: String,
    closeDialogClick: () -> Unit,
    monitorEmailVerification: () -> Unit
) {
    monitorEmailVerification()
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
                    text = stringResource(R.string.verify_your_email),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.waiting_email_confirmation, email),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = closeDialogClick,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}

@Composable
fun AgreementText(
    onPrivacyClick: () -> Unit,
    onTermsClick: () -> Unit
) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val annotatedString: AnnotatedString = buildAnnotatedString {
        append(stringResource(R.string.by_clicking_register))

        pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        ) { append(stringResource(R.string.reg_screen_pp)) }
        pop()

        append(stringResource(R.string.and))

        pushStringAnnotation(tag = "TERMS", annotation = "terms")
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        ) { append(stringResource(R.string.reg_screen_tou)) }
        pop()
    }

    BasicText(
        text = annotatedString,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle
        ),
        onTextLayout = { textLayoutResult = it },
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures { offsetPosition ->
                    textLayoutResult?.let { layoutResult ->
                        val position = layoutResult.getOffsetForPosition(offsetPosition)
                        annotatedString
                            .getStringAnnotations(tag = "PRIVACY", start = position, end = position)
                            .firstOrNull()?.let {
                                onPrivacyClick()
                            }
                        annotatedString
                            .getStringAnnotations(tag = "TERMS", start = position, end = position)
                            .firstOrNull()?.let {
                                onTermsClick()
                            }
                    }
                }
            }
    )
}

@Suppress("DEPRECATION")
@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}