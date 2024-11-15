package dev.dotingo.receptory.presentation.screens.timer_screen

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dotingo.receptory.utils.TimeFormatExt.timeFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class TimerViewModel : ViewModel() {
    private var _countDownTimer: CountDownTimer? = null
    private val _timeLeft = MutableStateFlow(0L)

    private var _userInputHour = MutableStateFlow(0L)
    private var _userInputMinute = MutableStateFlow(0L)
    private var _userInputSecond = MutableStateFlow(0L)

    private var _showTimer = MutableStateFlow(false)
    val showTimer: StateFlow<Boolean> = _showTimer
    fun setShowTimer(value: Boolean) {
        _showTimer.value = value
    }

    private val _isTimerFinished = MutableStateFlow(false)
    val isTimerFinished: StateFlow<Boolean> = _isTimerFinished

    private val _timerText = MutableStateFlow("")
    val timerText: StateFlow<String> = _timerText

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _circleProgress = MutableStateFlow(1f)
    val circleProgress: StateFlow<Float> = _circleProgress

    private fun calculateInitialTimeInMillis(): Long {
        val hour = TimeUnit.HOURS.toMillis(_userInputHour.value)
        val minute = TimeUnit.MINUTES.toMillis(_userInputMinute.value)
        val second = TimeUnit.SECONDS.toMillis(_userInputSecond.value)
        return hour + minute + second
    }

    fun startCountDownTimer() = viewModelScope.launch {
        _isPlaying.value = true
        _isTimerFinished.value = false
        if (_timeLeft.value == 0L) {
            _timeLeft.value = calculateInitialTimeInMillis()
        }
        _countDownTimer = object : CountDownTimer(_timeLeft.value, 1000L) {
            override fun onTick(currentTimeLeft: Long) {
                _timerText.value = currentTimeLeft.timeFormat()
                _timeLeft.value = currentTimeLeft
                _circleProgress.value =
                    ((currentTimeLeft / 1000) * 1000) / calculateInitialTimeInMillis().toFloat()
            }

            override fun onFinish() {
                _isTimerFinished.value = true
                _timerText.value = "00:00:00"
                _isPlaying.value = false
            }
        }
        _countDownTimer?.start()
        _countDownTimer?.onTick(_timeLeft.value)
    }

    fun stopCountDownTimer() {
        _isPlaying.value = false
        _countDownTimer?.cancel()
    }

    fun resetCountDownTimer() {
        _isPlaying.value = false
        _isTimerFinished.value = false
        _countDownTimer?.cancel()
        _timeLeft.value = calculateInitialTimeInMillis()
        _timerText.value = _timeLeft.value.timeFormat()
        _circleProgress.value = 1f
    }

    fun setUserTime(hours: Long, minutes: Long, seconds: Long) {
        _userInputHour.value = hours
        _userInputMinute.value = minutes
        _userInputSecond.value = seconds
        resetCountDownTimer()
    }

    fun addMinute() {
        _userInputMinute.value += 1
    }
}