package dev.dotingo.receptory.presentation.screens.settings_screen

import androidx.activity.compose.LocalActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.work.WorkManager
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.utils.appLanguages
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToRegistrationScreen: () -> Unit,
    navigateToCategoriesEditScreen: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val settingsUiState by viewModel.settingsState.collectAsStateWithLifecycle()

    var showLogoutDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }

    var selectedTheme by remember { mutableStateOf(settingsUiState.getThemeValue) }
    var themeLocation by remember { mutableIntStateOf(0) }

    val auth = viewModel.auth

    LaunchedEffect(Unit) {
        viewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
    }

    LaunchedEffect(settingsUiState.getThemeValue) {
        selectedTheme = if (settingsUiState.getThemeValue.isEmpty()) "Темная"
        else settingsUiState.getThemeValue
        themeLocation = themes.indexOf(selectedTheme).takeIf { it >= 0 } ?: 0
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(R.string.settings)) },
            navigationIcon = {
                CircleIcon(
                    modifier = Modifier.padding(start = smallPadding),
                    imageVector = BackArrowIcon,
                    contentDescription = stringResource(R.string.go_back)
                ) {
                    navigateBack()
                }
            })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            Text(
                stringResource(R.string.general_settings),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            ListItem(
                headlineContent = {
                    Text(
                        stringResource(R.string.app_lang_settings),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                supportingContent = {
                    Text(
                        appLanguages.find { it.code == settingsUiState.selectedLanguage }?.displayLanguage
                            ?: "Русский"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        showLanguageDialog = true
                    })
            )

            ListItem(
                headlineContent = {
                    Text(
                        stringResource(R.string.theme_settings),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                supportingContent = {
                    if (selectedTheme == "Темная") Text(stringResource(R.string.dark_mode)) else Text(
                        stringResource(R.string.light_mode)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        showThemeDialog = true
                    })
            )

            ListItem(
                headlineContent = {
                    Text(
                        stringResource(R.string.categories),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                supportingContent = { Text(stringResource(R.string.edit_categories)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        navigateToCategoriesEditScreen()
                    })
            )
            HorizontalDivider(Modifier.padding(vertical = 15.dp))
            Text(
                text = if (auth.currentUser == null) stringResource(R.string.account_settings) else auth.currentUser?.email
                    ?: stringResource(R.string.your_account),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            ListItem(
                headlineContent = {
                    Text(
                        if (auth.currentUser == null) stringResource(R.string.login_bt) else stringResource(
                            R.string.сhange_password
                        ),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                supportingContent = { if (auth.currentUser != null) Text(stringResource(R.string.change_password_current_account)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        viewModel.resetPassword(auth.currentUser?.email ?: "")
                        if (auth.currentUser == null) navigateToRegistrationScreen()
                        else { //nothing
                        }
                    })
            )
            if (auth.currentUser != null) {
                ListItem(
                    headlineContent = {
                        Text(
                            stringResource(R.string.logout),
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            showLogoutDialog = true
                        })
                )
            }

            if (showThemeDialog) {
                SingleSelectDialog(
                    modifier = modifier,
                    title = stringResource(id = R.string.select_theme),
                    optionsList = themes,
                    defaultSelected = themeLocation,
                    onSubmitButtonClick = { id ->
                        coroutineScope.launch {
                            viewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[id]))
                            viewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
                        }
                        showThemeDialog = false
                    },
                    onDismissRequest = { value ->
                        coroutineScope.launch {
                            viewModel.handleScreenEvents(SettingsScreenEvent.OpenThemeDialog(value))
                        }
                        showThemeDialog = false
                    }
                )
            }

            if (showLanguageDialog) {
                val activity = LocalActivity.current as? AppCompatActivity
                SingleSelectDialog(
                    modifier = modifier,
                    title = stringResource(R.string.select_language),
                    optionsList = appLanguages.map { it.displayLanguage },
                    defaultSelected = appLanguages.indexOfFirst { it.code == settingsUiState.selectedLanguage },
                    onSubmitButtonClick = { selectedIndex ->
                        val code = appLanguages[selectedIndex].code
                        activity?.let {
                            viewModel.changeLanguage(code)
                        }
                    },
                    onDismissRequest = { showLanguageDialog = false }
                )
            }
            if (showLogoutDialog) {
                LogoutDialog(
                    onDismissRequest = { showLogoutDialog = it },
                    onSubmitButtonClick = {
                        showThemeDialog = false
                        WorkManager.getInstance(context).cancelAllWork()
                        viewModel.signOut()
                        navigateToRegistrationScreen()
                    }
                )
            }
        }
    }
}