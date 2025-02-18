package dev.dotingo.receptory.presentation.screens.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.RadioButtonRow
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToRegistrationScreen: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    //delete

    val coroutineScope = rememberCoroutineScope()

    var showLanguageDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }

    var selectedLanguage by remember { mutableStateOf("Русский") }
    var selectedTheme by remember { mutableStateOf("Тёмная") }
    var checked by remember { mutableStateOf(true) }

    val settingsUiState by viewModel.settingsState.collectAsStateWithLifecycle()
    var themeLocation by remember {
        mutableIntStateOf(0)
    }
    val auth = viewModel.auth

    LaunchedEffect(key1 = settingsUiState.getThemeValue) {
        viewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
        themeLocation = if (settingsUiState.getThemeValue.isNullOrEmpty()) {
            0
        } else {
            themes.indexOf(settingsUiState.getThemeValue)
        }
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(R.string.settings)) },
            navigationIcon = {
                CircleIcon(
                    modifier = Modifier.padding(start = smallPadding),
                    imageVector = BackArrowIcon,
                    contentDescription = "Назад"
                ) {
                    navigateBack()
                }
            })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = commonHorizontalPadding)
        ) {
            Text(
                stringResource(R.string.general_settings),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            SettingsMenuItem(
                title = stringResource(R.string.app_lang_settings),
                option = selectedLanguage
            ) {
                showLanguageDialog = true
            }
            if (showLanguageDialog) {
                SettingsRadioDialog(
                    title = stringResource(R.string.select_language),
                    options = listOf("Системный","Русский", "English"),
                    selectedOption = selectedLanguage,
                    onDismissRequest = { showLanguageDialog = false },
                    onOptionSelected = {
                        selectedLanguage = it
                        showLanguageDialog = false
                    }
                )
            }

            SettingsMenuItem(
                title = stringResource(R.string.theme_settings),
                option = selectedTheme
            ) {
                showThemeDialog = true
            }
            if (showThemeDialog) {
                SingleSelectDialog(modifier = modifier,
                    title = stringResource(id = R.string.select_theme),
                    optionsList = themes,
                    defaultSelected = themeLocation,
                    submitButtonText = stringResource(id = R.string.ok),
                    dismissButtonText = stringResource(id = R.string.cancel),
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
                    })
            }
            SettingsMenuItem(
                title = stringResource(R.string.categories),
                option = stringResource(R.string.edit_categories)
            ) {

            }
            SettingsMenuItemWithSwitch(
                title = stringResource(
                    R.string.timer_vibration_settings
                ),
                option = stringResource(R.string.enable_timer_vibration),
                checked = checked
            ) {
                checked = it
            }
            HorizontalDivider(Modifier.padding(vertical = 15.dp))
            Text(
                text = if (auth.currentUser == null) stringResource(R.string.account_settings) else auth.currentUser?.email
                    ?: stringResource(R.string.your_account),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            if (auth.currentUser == null) {
                SettingsMenuItem(
                    title = stringResource(R.string.login_bt)
                ) {
                    navigateToRegistrationScreen()
                }
            } else {
                SettingsMenuItem(
                    title = stringResource(R.string.сhange_password),
                    option = stringResource(R.string.change_password_current_account)
                ) { }
                SettingsMenuItem(
                    title = stringResource(R.string.logout)
                ) {
                    viewModel.signOut()
                    navigateToRegistrationScreen()
                }
            }

            HorizontalDivider(Modifier.padding(vertical = 15.dp))
            Text(
                stringResource(R.string.storage),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                stringResource(R.string.export_recipes),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun SettingsMenuItem(
    modifier: Modifier = Modifier,
    title: String,
    option: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .weight(1f)
        ) {
            Text(
                title,
                fontWeight = FontWeight.SemiBold
            )
            if (option != null) {
                Text(
                    option,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SettingsMenuItemWithSwitch(
    modifier: Modifier = Modifier,
    title: String,
    option: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .weight(1f)
        ) {
            Text(
                title,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                option,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Switch(checked, { onCheckedChange(it) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRadioDialog(
    title: String,
    options: List<String>,
    selectedOption: String,
    onDismissRequest: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
    ){
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(Modifier.selectableGroup()) {
                    options.forEach { option ->
                        RadioButtonRow(
                            text = option,
                            selectedOption = selectedOption,
                            onOptionSelected = {
                                onOptionSelected(it)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = onDismissRequest
                ) { Text(stringResource(R.string.ok)) }
            }
        }
    }
}