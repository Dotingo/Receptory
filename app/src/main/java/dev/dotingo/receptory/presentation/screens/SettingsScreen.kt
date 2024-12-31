package dev.dotingo.receptory.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToRegistrationScreen: () -> Unit
) {

    //delete
    val auth = Firebase.auth

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Настройки") },
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
                "Общие",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            MenuSettings(title = "Язык приложения", option = "Русский")
            Spacer(Modifier.height(5.dp))
            MenuSettings(title = "Тема", option = "Тёмная")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Вибрация таймера",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Switch(true, {})
            }
            HorizontalDivider(Modifier.padding(vertical = 15.dp))
            Text(
                text = if (auth.currentUser == null) "Аккаунт" else auth.currentUser?.email
                    ?: "Ваш аккаунт",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            if (auth.currentUser == null) {
                Text(
                    "Войти",
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(
                    "Сменить пароль",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Выйти",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        signOut(auth)
                        navigateToRegistrationScreen()
                    }
                )
            }

            HorizontalDivider(Modifier.padding(vertical = 15.dp))
            Text(
                "Хранилище",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                "Выгрузить рецепты",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun MenuSettings(modifier: Modifier = Modifier, title: String, option: String) {
    Column(modifier = modifier.clickable {

    }) {
        Text(
            title,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            option,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
    }
}

private fun signOut(auth: FirebaseAuth) {
    auth.signOut()
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    ReceptoryTheme {
        SettingsScreen(navigateBack = {}) { }
    }
}