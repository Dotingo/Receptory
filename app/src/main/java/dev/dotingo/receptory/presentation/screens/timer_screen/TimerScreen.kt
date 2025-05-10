package dev.dotingo.receptory.presentation.screens.timer_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.icons.ClearInputIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.theme.Dimens.circleTimerButtonSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraBigPadding
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding
import dev.dotingo.receptory.utils.clickVibration
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    timerViewModel: TimerViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val timerText by timerViewModel.timerText.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.timer)) },
                navigationIcon = {
                    CircleIcon(
                        imageVector = BackArrowIcon,
                        modifier = Modifier.padding(start = smallPadding),
                        contentDescription = stringResource(R.string.go_back),
                        onClick = navigateBack
                    )
                }
            )
        },
        bottomBar = {
            val timerName = stringResource(R.string.timer_name)
            ReceptoryMainButton(
                modifier = Modifier
                    .padding(top = smallPadding)
                    .padding(horizontal = commonHorizontalPadding)
                    .navigationBarsPadding(),
                text = stringResource(R.string.add_timer),
                enabled = timerText != "00:00:00"
            ) {
                timerViewModel.startSystemTimer(context, timerName)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TimerInput(
                modifier = Modifier
                    .weight(1f),
                viewModel = timerViewModel
            )
        }
    }
}

@Composable
fun TimerInput(modifier: Modifier = Modifier, viewModel: TimerViewModel) {
    var input by remember { mutableStateOf("") }

    val formattedTime = remember(input) {
        formatTime(input, viewModel)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = formattedTime,
            fontSize = 46.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))
        Keyboard(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = extraBigPadding),
            onKeyPress = { key ->
                when (key) {
                    "clear" -> if (input.isNotEmpty()) input = input.dropLast(1)
                    "00" -> if (input.isNotEmpty() && input.any { it != '0' } && input.length < 5) {
                        input += "00"
                    } else if (input.length == 5) {
                        input += "0"
                    }

                    "0" -> if (input.isNotEmpty() && input.any { it != '0' } && input.length < 6) {
                        input += "0"
                    }

                    else -> if (input.length < 6) input += key
                }
            }
        )
    }
}


private fun formatTime(input: String, viewModel: TimerViewModel): AnnotatedString {
    val digits = input.padStart(6, '0')
    val hours = digits.take(digits.length - 4).toIntOrNull() ?: 0
    val minutes = digits.takeLast(4).take(2).toIntOrNull() ?: 0
    val seconds = digits.takeLast(2).toIntOrNull() ?: 0

    viewModel.setUserTime(hours.toLong(), minutes.toLong(), seconds.toLong())

    val locale = Locale.getDefault()
    val isEnglish = locale.language == "en"

    val hourLabel = if (isEnglish) "h" else "ч"
    val minuteLabel = if (isEnglish) "m" else "м"
    val secondLabel = if (isEnglish) "s" else "с"

    return buildAnnotatedString {
        append(String.format(locale, "%02d", hours))
        withStyle(SpanStyle(fontSize = 20.sp)) { append("$hourLabel ") }

        append(String.format(locale, "%02d", minutes))
        withStyle(SpanStyle(fontSize = 20.sp)) { append("$minuteLabel ") }

        append(String.format(locale, "%02d", seconds))
        withStyle(SpanStyle(fontSize = 20.sp)) { append(secondLabel) }
    }
}

@Composable
private fun Keyboard(modifier: Modifier = Modifier, onKeyPress: (String) -> Unit) {
    val keys = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("00", "0", "clear")
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        for (row in keys) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(tinyPadding),
                modifier = Modifier.padding(top = tinyPadding)
            ) {
                for (key in row) {
                    KeyButton(
                        key = key,
                        onClick = { onKeyPress(key) }
                    )
                }
            }
        }
    }
}

@Composable
private fun KeyButton(key: String, onClick: () -> Unit) {
    val context = LocalContext.current
    Button(
        onClick = {
            clickVibration(context)
            onClick()
        },
        shape = CircleShape,
        modifier = Modifier
            .size(circleTimerButtonSize),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (key == "clear") MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.secondaryContainer,
            contentColor = if (key == "clear") MaterialTheme.colorScheme.onPrimaryContainer
            else MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        if (key == "clear") {
            Icon(imageVector = ClearInputIcon, "")
        } else {
            Text(
                text = key, fontSize = 32.sp
            )
        }
    }
}

