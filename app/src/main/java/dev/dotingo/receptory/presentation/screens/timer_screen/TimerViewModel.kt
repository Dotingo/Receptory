package dev.dotingo.receptory.presentation.screens.timer_screen

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import java.util.concurrent.TimeUnit

class TimerViewModel: ViewModel() {

    private val _timeLeft = MutableStateFlow(0L)
    private val _initialDuration = MutableStateFlow(0L)
    private val _timerText = MutableStateFlow("00:00:00")
    val timerText: StateFlow<String> = _timerText

    private fun resetInternalState() {
        _timeLeft.value = _initialDuration.value
        _timerText.value = _initialDuration.value.timeFormatCorrected()
    }

    fun setUserTime(hours: Long, minutes: Long, seconds: Long) {
        val duration = TimeUnit.HOURS.toMillis(hours) +
                TimeUnit.MINUTES.toMillis(minutes) +
                TimeUnit.SECONDS.toMillis(seconds)
        _initialDuration.value = duration
        resetInternalState()
        _timeLeft.value = duration
        _timerText.value = duration.timeFormatCorrected()
    }

    fun startSystemTimer(context: Context, timerName: String) {
        val totalSeconds = (_initialDuration.value / 1000).toInt()
        if (totalSeconds > 0) {
            val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_LENGTH, totalSeconds)
                putExtra(AlarmClock.EXTRA_MESSAGE, timerName)
                putExtra(AlarmClock.EXTRA_SKIP_UI, false)
            }
            context.startActivity(intent)
        }
    }

    private fun Long.timeFormatCorrected(): String {
        val adjustedSeconds = (this + 999) / 1000
        val displayHours = TimeUnit.SECONDS.toHours(adjustedSeconds)
        val displayMinutes = TimeUnit.SECONDS.toMinutes(adjustedSeconds) % 60
        val displaySeconds = adjustedSeconds % 60
        return String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            displayHours,
            displayMinutes,
            displaySeconds
        )
    }
}