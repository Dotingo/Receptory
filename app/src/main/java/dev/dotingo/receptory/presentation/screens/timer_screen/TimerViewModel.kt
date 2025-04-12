package dev.dotingo.receptory.presentation.screens.timer_screen

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.receptory.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.max
import androidx.core.content.edit

private const val TIMER_PREFS = "timer_prefs"
private const val KEY_FINISH_TIME = "finish_time_millis"
private const val KEY_INITIAL_DURATION = "initial_duration_millis"
private const val KEY_IS_RUNNING = "is_running"
private const val KEY_SHOW_TIMER_UI = "show_timer_ui"

private const val TIMER_ALARM_REQUEST_CODE = 123

@HiltViewModel
class TimerViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val appContext = application.applicationContext
    private val prefs: SharedPreferences =
        appContext.getSharedPreferences(TIMER_PREFS, Context.MODE_PRIVATE)
    private val alarmManager = appContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var _countDownTimer: CountDownTimer? = null
    private val _timeLeft = MutableStateFlow(0L)
    private val _initialDuration = MutableStateFlow(0L)
    private val _showTimer = MutableStateFlow(prefs.getBoolean(KEY_SHOW_TIMER_UI, false))
    val showTimer: StateFlow<Boolean> = _showTimer
    private val _isTimerFinished = MutableStateFlow(false)
    val isTimerFinished: StateFlow<Boolean> = _isTimerFinished
    private val _timerText = MutableStateFlow("00:00:00")
    val timerText: StateFlow<String> = _timerText
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying
    private val _circleProgress = MutableStateFlow(1f)
    val circleProgress: StateFlow<Float> = _circleProgress

    @Volatile
    private var mediaPlayer: MediaPlayer? = null
    private var soundJob: Job? = null

    init {
        restoreTimerState()
    }

    private fun saveTimerState(
        finishTimeMillis: Long,
        initialDurationMillis: Long,
        isRunning: Boolean
    ) {
        prefs.edit {
            putLong(KEY_FINISH_TIME, finishTimeMillis)
                .putLong(KEY_INITIAL_DURATION, initialDurationMillis)
                .putBoolean(KEY_IS_RUNNING, isRunning)
                .putBoolean(KEY_SHOW_TIMER_UI, true)
        }
    }

    private fun clearSavedState() {
        prefs.edit {
            remove(KEY_FINISH_TIME)
                .remove(KEY_INITIAL_DURATION)
                .remove(KEY_IS_RUNNING)
                .remove(KEY_SHOW_TIMER_UI)
        }
    }

    private fun restoreTimerState() {
        val finishTime = prefs.getLong(KEY_FINISH_TIME, 0L)
        val initialDuration = prefs.getLong(KEY_INITIAL_DURATION, 0L)
        val isRunning = prefs.getBoolean(KEY_IS_RUNNING, false)
        val showUi = prefs.getBoolean(KEY_SHOW_TIMER_UI, false)

        if (finishTime > 0 && initialDuration > 0 && showUi) {
            val currentTime = System.currentTimeMillis()
            if (finishTime > currentTime) {
                val remainingTime = finishTime - currentTime
                _timeLeft.value = remainingTime
                _initialDuration.value = initialDuration
                _timerText.value = remainingTime.timeFormatCorrected()
                _circleProgress.value = max(0f, remainingTime.toFloat() / initialDuration.toFloat())
                _isPlaying.value = isRunning
                _showTimer.value = true
                _isTimerFinished.value = false

                if (isRunning) {
                    startCountDownTimerInternal(remainingTime, initialDuration)
                } else {
                    _isPlaying.value = false
                }

            } else {
                _showTimer.value = true
                _isTimerFinished.value = true
                _timerText.value = 0L.timeFormatCorrected()
                _circleProgress.value = 0f
                _isPlaying.value = false
                clearSavedState()
            }
        } else {
            _showTimer.value = false
            resetInternalState()
        }
    }

    private fun startCountDownTimerInternal(duration: Long, initialDuration: Long) {
        _countDownTimer?.cancel()
        _isPlaying.value = true
        _isTimerFinished.value = false
        _timeLeft.value = duration
        _initialDuration.value = initialDuration

        _countDownTimer = object : CountDownTimer(duration, 100L) {
            override fun onTick(currentTimeLeft: Long) {
                val currentDuration = _initialDuration.value
                if (currentDuration > 0) {
                    _timeLeft.value = currentTimeLeft
                    _timerText.value = currentTimeLeft.timeFormatCorrected()
                    _circleProgress.value =
                        max(0f, currentTimeLeft.toFloat() / currentDuration.toFloat())
                }
            }

            override fun onFinish() {
                Log.d("TimerViewModel", "CountDownTimer onFinish triggered.")
                _isTimerFinished.value = true
                _timerText.value = 0L.timeFormatCorrected()
                _circleProgress.value = 0f
                _isPlaying.value = false
                clearSavedState()
                cancelAlarm()
            }
        }.start()
        _timerText.value = duration.timeFormatCorrected()
        if (initialDuration > 0) {
            _circleProgress.value = max(0f, duration.toFloat() / initialDuration.toFloat())
        } else {
            _circleProgress.value = 1f
        }
    }

    fun startCountDownTimer() {
        if (_isPlaying.value) return
        val initialDuration = _initialDuration.value
        val duration =
            if (_timeLeft.value <= 0L || _timeLeft.value == initialDuration) initialDuration else _timeLeft.value
        if (duration <= 0L) return
        val finishTime = System.currentTimeMillis() + duration
        saveTimerState(finishTime, initialDuration, true)
        scheduleAlarm(finishTime)
        startCountDownTimerInternal(duration, initialDuration)
    }

    fun stopCountDownTimer() {
        if (!_isPlaying.value) return
        _countDownTimer?.cancel()
        _isPlaying.value = false
        val finishTime = System.currentTimeMillis() + _timeLeft.value
        saveTimerState(finishTime, _initialDuration.value, false)
    }

    fun resetCountDownTimer() {
        Log.d("TimerViewModel", "resetCountDownTimer START")
        _countDownTimer?.cancel()
        _isPlaying.value = false
        _isTimerFinished.value = false
        stopAlarmSound()
        cancelAlarm()
        clearSavedState()
        resetInternalState()
        Log.d("TimerViewModel", "resetCountDownTimer FINISH (async cancels/stops started)")
    }

    private fun resetInternalState() {
        Log.d("TimerViewModel", "resetInternalState START")
        _timeLeft.value = _initialDuration.value
        _timerText.value = _initialDuration.value.timeFormatCorrected()
        _circleProgress.value = 1f
        Log.d("TimerViewModel", "resetInternalState FINISH")
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

    fun addMinute() {
        val oneMinuteMillis = TimeUnit.MINUTES.toMillis(1)
        if (_isTimerFinished.value) return
        val newTimeLeft = _timeLeft.value + oneMinuteMillis
        _initialDuration.value += oneMinuteMillis
        _timeLeft.value = newTimeLeft
        _timerText.value = newTimeLeft.timeFormatCorrected()
        if (_initialDuration.value > 0) {
            _circleProgress.value =
                max(0f, newTimeLeft.toFloat() / _initialDuration.value.toFloat())
        }
        val newFinishTime = System.currentTimeMillis() + newTimeLeft
        saveTimerState(newFinishTime, _initialDuration.value, _isPlaying.value)
        scheduleAlarm(newFinishTime)
        if (_isPlaying.value) {
            _countDownTimer?.cancel()
            startCountDownTimerInternal(newTimeLeft, _initialDuration.value)
        }
    }

    fun setShowTimer(value: Boolean) {
        Log.d("TimerViewModel", "setShowTimer($value) START")
        if (_showTimer.value != value) {
            _showTimer.value = value
            prefs.edit { putBoolean(KEY_SHOW_TIMER_UI, value) }
            Log.d("TimerViewModel", "setShowTimer($value): StateFlow and Prefs updated")
        } else {
            Log.d("TimerViewModel", "setShowTimer($value): Value already set, skipped update.")
        }
        Log.d("TimerViewModel", "setShowTimer($value) FINISH")
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

    private fun getAlarmPendingIntent(): PendingIntent {
        val intent = Intent(appContext, TimerAlarmReceiver::class.java).apply {
            action =
                ACTION_TIMER_FINISHED
        }
        val flags =
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        return PendingIntent.getBroadcast(appContext, TIMER_ALARM_REQUEST_CODE, intent, flags)
    }

    private fun scheduleAlarm(finishTimeMillis: Long) {
        // Запускаем в фоновом потоке, чтобы не блокировать UI
        viewModelScope.launch(Dispatchers.IO) {
            // 1. Проверяем разрешение SCHEDULE_EXACT_ALARM (только для Android 12/S/API 31 и выше)
            var canScheduleExact = true // По умолчанию считаем, что можем (для < Android 12)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                canScheduleExact = alarmManager.canScheduleExactAlarms()
            }

            Log.d("TimerViewModel", "[IO] scheduleAlarm START for $finishTimeMillis. Can schedule exact alarms: $canScheduleExact")

            // 2. Если точные будильники нужны (Android 12+) и разрешение НЕ предоставлено -> выводим предупреждение и НЕ планируем
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExact) {
                Log.e("TimerViewModel", "[IO] SCHEDULE_EXACT_ALARM permission NOT granted. Cannot schedule exact alarm. Timer might not work reliably in background!")
                // ВАЖНО: Не планируем будильник, так как точность не гарантирована.
                // UI должен был предотвратить запуск таймера или предупредить пользователя.
                return@launch // Выходим из корутины
            }

            // 3. Получаем PendingIntent для нашего BroadcastReceiver
            val pendingIntent = getAlarmPendingIntent() // Предполагается, что getAlarmPendingIntent() существует и корректен

            // 4. Планируем будильник в зависимости от версии Android
            try {
                when {
                    // Для Android 6.0 (M/API 23) и выше (включая S+ с уже проверенным разрешением)
                    // Используем setExactAndAllowWhileIdle для работы в Doze режиме
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                        alarmManager.setExactAndAllowWhileIdle(
                            AlarmManager.RTC_WAKEUP, // Тип: реальное время, будить устройство
                            finishTimeMillis,        // Время срабатывания
                            pendingIntent            // Что запустить
                        )
                        Log.d("TimerViewModel", "[IO] scheduleAlarm: setExactAndAllowWhileIdle scheduled successfully.")
                    }
                    // Для версий до Android 6.0 (M/API 23)
                    // Используем setExact
                    else -> {
                        alarmManager.setExact(
                            AlarmManager.RTC_WAKEUP,
                            finishTimeMillis,
                            pendingIntent
                        )
                        Log.d("TimerViewModel", "[IO] scheduleAlarm: setExact scheduled successfully (for pre-M).")
                    }
                }
            } catch (se: SecurityException) {
                // Может возникнуть, если разрешения USE_EXACT_ALARM/SCHEDULE_EXACT_ALARM отсутствуют в манифесте
                // или система по какой-то причине их отозвала (маловероятно для USE_EXACT_ALARM)
                Log.e("TimerViewModel", "[IO] scheduleAlarm failed due to SecurityException. Check Manifest permissions (USE_EXACT_ALARM/SCHEDULE_EXACT_ALARM)?", se)
            } catch (e: Exception) {
                // Другие возможные ошибки при работе с AlarmManager
                Log.e("TimerViewModel", "[IO] scheduleAlarm failed with generic exception.", e)
            }

            Log.d("TimerViewModel", "[IO] scheduleAlarm FINISH")
        }
    }

    private fun releaseMediaPlayer() {
        try {
            mediaPlayer?.release()
        } catch (e: Exception) {
            Log.e("TimerViewModel", "Error releasing MediaPlayer", e)
        } finally {
            mediaPlayer = null
            Log.d("TimerViewModel", "MediaPlayer released and set to null.")
        }
    }

    fun playAlarmSound() {
        soundJob?.cancel()
        soundJob = viewModelScope.launch(Dispatchers.IO) {
            if (!_showTimer.value) {
                Log.d("TimerViewModel", "playAlarmSound: Skipped because showTimer is false.")
                return@launch
            }

            releaseMediaPlayer()
            Log.d("TimerViewModel", "playAlarmSound: Creating new MediaPlayer instance.")
            try {
                mediaPlayer = MediaPlayer.create(appContext, R.raw.timer_alarm)
                mediaPlayer?.apply {
                    setOnErrorListener { mp, what, extra ->
                        Log.e("TimerViewModel", "MediaPlayer error: what=$what, extra=$extra")
                        releaseMediaPlayer()
                        true
                    }
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build()
                    )
                    isLooping = true
                    start()
                    Log.d("TimerViewModel", "MediaPlayer started playing.")
                } ?: Log.w("TimerViewModel", "MediaPlayer.create returned null.")
            } catch (e: Exception) {
                Log.e("TimerViewModel", "Error creating/starting MediaPlayer in playAlarmSound", e)
                releaseMediaPlayer()
            }
        }
    }

    fun stopAlarmSound() {
        soundJob?.cancel()
        soundJob = viewModelScope.launch(Dispatchers.IO) {
            if (mediaPlayer != null) {
                Log.d("TimerViewModel", "stopAlarmSound: Releasing MediaPlayer.")
                releaseMediaPlayer()
            } else {
                Log.d("TimerViewModel", "stopAlarmSound: MediaPlayer is already null.")
            }
        }
    }

    private fun cancelAlarm() {
        val pendingIntent = getAlarmPendingIntent()
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TimerViewModel", "onCleared called.")
        _countDownTimer?.cancel()
        cancelAlarm()
        soundJob?.cancel()
        releaseMediaPlayer()
    }

    fun hideTimerAndCleanup() {
        Log.d("TimerViewModel", "hideTimerAndCleanup START")
        _countDownTimer?.cancel()
        cancelAlarm()
        clearSavedState()

        if (_showTimer.value) {
            _showTimer.value = false
            prefs.edit { putBoolean(KEY_SHOW_TIMER_UI, false) }
            Log.d("TimerViewModel", "hideTimerAndCleanup: _showTimer set to false and saved.")
        }

        stopAlarmSound()

        Log.d("TimerViewModel", "hideTimerAndCleanup FINISH (async cancels/stops started)")
    }
}