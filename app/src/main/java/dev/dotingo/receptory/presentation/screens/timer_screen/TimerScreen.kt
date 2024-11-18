package dev.dotingo.receptory.presentation.screens.timer_screen

import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.dotingo.receptory.R
import dev.dotingo.receptory.presentation.components.CircleIcon
import dev.dotingo.receptory.presentation.components.ReceptoryMainButton
import dev.dotingo.receptory.ui.icons.ClearInputIcon
import dev.dotingo.receptory.ui.icons.CloseIcon
import dev.dotingo.receptory.ui.icons.FinishIcon
import dev.dotingo.receptory.ui.icons.PauseIcon
import dev.dotingo.receptory.ui.icons.PlayIcon
import dev.dotingo.receptory.ui.icons.arrows.BackArrowIcon
import dev.dotingo.receptory.ui.icons.arrows.RefreshIcon
import dev.dotingo.receptory.ui.theme.Dimens.buttonShapeSize
import dev.dotingo.receptory.ui.theme.Dimens.circleTimerButtonSize
import dev.dotingo.receptory.ui.theme.Dimens.circleTimerProgressLineSize
import dev.dotingo.receptory.ui.theme.Dimens.circleTimerProgressSize
import dev.dotingo.receptory.ui.theme.Dimens.commonHorizontalPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraBigPadding
import dev.dotingo.receptory.ui.theme.Dimens.extraSmallPadding
import dev.dotingo.receptory.ui.theme.Dimens.mediumIconSize
import dev.dotingo.receptory.ui.theme.Dimens.smallPadding
import dev.dotingo.receptory.ui.theme.Dimens.timerControlsButtonSize
import dev.dotingo.receptory.ui.theme.Dimens.tinyPadding
import dev.dotingo.receptory.ui.theme.ReceptoryTheme
import dev.dotingo.receptory.utils.clickVibration
import dev.dotingo.receptory.utils.repeatVibration
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    timerViewModel: TimerViewModel = viewModel(),
    navigateBack: () -> Unit
) {

    val showTimer by timerViewModel.showTimer.collectAsStateWithLifecycle()
    val timerText by timerViewModel.timerText.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Таймер") },
                navigationIcon = {
                    CircleIcon(
                        imageVector = BackArrowIcon,
                        contentDescription = "Назад",
                        onClick = navigateBack
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            if (showTimer) {
                TimerCard(
                    timerViewModel = timerViewModel,
                    circleModifier = Modifier.size(circleTimerProgressSize)
                )
                Spacer(Modifier.weight(1f))

                TimerControls(
                    timerViewModel = timerViewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = extraBigPadding)
                        .navigationBarsPadding(),
                )
            } else {
                TimerInput(
                    modifier = Modifier
                        .weight(1f),
                    viewModel = timerViewModel
                )
                ReceptoryMainButton(
                    modifier = Modifier
                        .padding(horizontal = commonHorizontalPadding)
                        .padding(top = smallPadding)
                        .navigationBarsPadding(),
                    text = "Добавить таймер",
                    enabled = timerText != "00:00:00"
                ) {
                    timerViewModel.setShowTimer(true)
                }
            }
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

fun formatTime(input: String, viewModel: TimerViewModel): AnnotatedString {
    val digits = input.padStart(6, '0')
    val hours = digits.take(digits.length - 4).toIntOrNull() ?: 0
    val minutes = digits.takeLast(4).take(2).toIntOrNull() ?: 0
    val seconds = digits.takeLast(2).toIntOrNull() ?: 0

    viewModel.setUserTime(hours.toLong(), minutes.toLong(), seconds.toLong())

    return buildAnnotatedString {
        append(String.format(Locale("ru"), "%02d", hours))
        withStyle(SpanStyle(fontSize = 20.sp)) { append("ч ") }

        append(String.format(Locale("ru"), "%02d", minutes))
        withStyle(SpanStyle(fontSize = 20.sp)) { append("м ") }

        append(String.format(Locale("ru"), "%02d", seconds))
        withStyle(SpanStyle(fontSize = 20.sp)) { append("с") }
    }
}

@Composable
fun Keyboard(modifier: Modifier = Modifier, onKeyPress: (String) -> Unit) {
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
fun KeyButton(key: String, onClick: () -> Unit) {
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

@Composable
fun TimerCard(
    timerViewModel: TimerViewModel,
    circleModifier: Modifier = Modifier,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    backCircleColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    val progress by timerViewModel.circleProgress.collectAsStateWithLifecycle()
    val timerText by timerViewModel.timerText.collectAsStateWithLifecycle()
    val isTimerFinished by timerViewModel.isTimerFinished.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.timer_alarm).apply {
            isLooping = true
        }
    }

    if (isTimerFinished) {
        mediaPlayer.start()
        @Suppress("KotlinConstantConditions")
        LaunchedEffect(isTimerFinished) {
            repeatVibration(context, isTimerFinished)
        }
    } else {
        mediaPlayer.stop()
        mediaPlayer.prepare()
    }

    LaunchedEffect(Unit) {
        timerViewModel.startCountDownTimer()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .padding(horizontal = commonHorizontalPadding)
    ) {
        Box(
            modifier = Modifier
                .padding(smallPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircleIcon(
                imageVector = CloseIcon,
                modifier = Modifier
                    .size(mediumIconSize)
                    .align(Alignment.TopEnd),
                backgroundColor = MaterialTheme.colorScheme.secondary,
                iconColor = MaterialTheme.colorScheme.onSecondary
            ) {
                mediaPlayer?.release()
                mediaPlayer = null
                timerViewModel.setShowTimer(false)
            }
            Box(
                modifier = circleModifier
                    .padding(top = smallPadding)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = circleModifier) {
                    drawArc(
                        color = backCircleColor,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(
                            width = circleTimerProgressLineSize.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                    drawArc(
                        color = circleColor,
                        sweepAngle = 360 * progress,
                        startAngle = -90f,
                        useCenter = false,
                        style = Stroke(
                            width = circleTimerProgressLineSize.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }
                Text(
                    text = timerText,
                    fontSize = 36.sp
                )
                IconButton(
                    onClick = { timerViewModel.resetCountDownTimer() },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = smallPadding)
                ) {
                    Icon(
                        imageVector = RefreshIcon,
                        contentDescription = "Сбросить время",
                        modifier = Modifier.size(mediumIconSize),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun TimerControls(
    timerViewModel: TimerViewModel,
    modifier: Modifier = Modifier
) {
    val isPlaying by timerViewModel.isPlaying.collectAsStateWithLifecycle()
    val isFinished by timerViewModel.isTimerFinished.collectAsStateWithLifecycle()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        if (!isFinished) {
            Button(
                onClick = { timerViewModel.addMinute() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier.height(timerControlsButtonSize),
                shape = RoundedCornerShape(buttonShapeSize),
            ) {
                Text(
                    "+1:00", color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.width(extraSmallPadding))
        }

        Button(
            onClick = {
                if (isPlaying) {
                    timerViewModel.stopCountDownTimer()
                } else if (isFinished) {
                    timerViewModel.resetCountDownTimer()
                } else {
                    timerViewModel.startCountDownTimer()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.height(timerControlsButtonSize),
            shape = RoundedCornerShape(buttonShapeSize),
        ) {
            Icon(
                imageVector = if (isPlaying) {
                    PauseIcon
                } else if (isFinished) {
                    FinishIcon
                } else {
                    PlayIcon
                },
                contentDescription = "",
                modifier = Modifier.padding(smallPadding),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TimerScreenPreview() {
    ReceptoryTheme {
        TimerScreen {}
    }
}