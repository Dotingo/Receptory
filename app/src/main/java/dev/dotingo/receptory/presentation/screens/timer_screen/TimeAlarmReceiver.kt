package dev.dotingo.receptory.presentation.screens.timer_screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.net.toUri
import dev.dotingo.receptory.MainActivity
import dev.dotingo.receptory.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TIMER_PREFS = "timer_prefs"
private const val KEY_FINISH_TIME = "finish_time_millis"
private const val KEY_INITIAL_DURATION = "initial_duration_millis"
private const val KEY_IS_RUNNING = "is_running"
const val ACTION_TIMER_FINISHED =
    "dev.dotingo.receptory.presentation.screens.timer_screen.ACTION_TIMER_FINISHED"
private const val NOTIFICATION_CHANNEL_ID = "timer_channel"
private const val NOTIFICATION_CHANNEL_NAME = "Timer Alerts"
private const val NOTIFICATION_ID = 456

class TimerAlarmReceiver : BroadcastReceiver() {

    private val receiverScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TimerAlarmReceiver", "Received intent with action: ${intent.action}")

        if (intent.action == ACTION_TIMER_FINISHED) {
            Log.d("TimerAlarmReceiver", "Timer finished event received!")
            val pendingResult = goAsync()

            receiverScope.launch {
                try {
                    createNotificationChannel(context)
                    showNotification(context)
                    playSound(context)
                    clearTimerState(context)
                    Log.d("TimerAlarmReceiver", "Timer finished processing complete.")
                } catch (e: Exception) {
                    Log.e("TimerAlarmReceiver", "Error processing timer finished event", e)
                } finally {
                    pendingResult.finish()
                    Log.d("TimerAlarmReceiver", "Pending result finished.")
                    // Опционально можно отменить корутину, если она больше не нужна
                    receiverScope.coroutineContext.cancelChildren()
                }
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for timer completion"
                enableLights(true)
                lightColor = ContextCompat.getColor(context, R.color.teal_200)
//                enableVibration(true)
                // Можно задать кастомный звук и вибрацию для канала,
                // но мы управляем ими отдельно ниже для большей гибкости
                // setSound(null, null)
                // vibrationPattern = null
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d("TimerAlarmReceiver", "Notification channel created or already exists.")
        }
    }

    private fun showNotification(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val resultIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(resultIntent)
            val flags =
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            getPendingIntent(0, flags)
        }


        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_timer)
            .setContentTitle(context.getString(R.string.timer_finished_title))
            .setContentText(context.getString(R.string.timer_finished_title))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)
        // .setSound(null) // Отключаем звук уведомления, т.к. управляем им вручную ниже

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            try {
                notificationManager.notify(NOTIFICATION_ID, builder.build())
                Log.d("TimerAlarmReceiver", "Notification shown with ID: $NOTIFICATION_ID")
            } catch (e: SecurityException) {
                Log.e("TimerAlarmReceiver", "Missing POST_NOTIFICATIONS permission?", e)
            } catch (e: Exception) {
                Log.e("TimerAlarmReceiver", "Failed to show notification", e)
            }
        } else {
            Log.w(
                "TimerAlarmReceiver",
                "POST_NOTIFICATIONS permission not granted. Cannot show notification."
            )
        }
    }

    private suspend fun playSound(context: Context) {
        try {
            val customSoundUri =
                "android.resource://${context.packageName}/${R.raw.timer_alarm}".toUri()
            Log.d("TimerAlarmReceiver", "Attempting to play sound: $customSoundUri")
            val ringtone = RingtoneManager.getRingtone(context, customSoundUri)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ringtone.isLooping =
                    false
            }
            ringtone.audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            ringtone.play()
            // Dремя на проигрывание звука перед завершением goAsync
            // Можно остановить рингтон по таймеру.
            delay(4000)
            if (ringtone.isPlaying) {
                ringtone.stop()
                Log.d("TimerAlarmReceiver", "Ringtone stopped after delay.")
            }

        } catch (e: Exception) {
            Log.e("TimerAlarmReceiver", "Error playing sound", e)
        }

        // Вариант 2: Использование MediaPlayer (сложнее в ресивере)
        // Потребует больше кода для управления жизненным циклом плеера,
        // обработки ошибок и, возможно, Foreground Service для надежности.
        // val mediaPlayer = MediaPlayer.create(context, R.raw.timer_alarm)
        // mediaPlayer?.setAudioAttributes(...)
        // mediaPlayer?.isLooping = true // или false
        // mediaPlayer?.start()
        // delay(...) // или mediaPlayer?.setOnCompletionListener { mediaPlayer.release() }
        // mediaPlayer?.stop()
        // mediaPlayer?.release()
    }

    private fun clearTimerState(context: Context) {
        try {
            val prefs: SharedPreferences =
                context.getSharedPreferences(TIMER_PREFS, Context.MODE_PRIVATE)
            prefs.edit{
                remove(KEY_FINISH_TIME)
                    .remove(KEY_INITIAL_DURATION)
                    .remove(KEY_IS_RUNNING)
            }
            Log.d("TimerAlarmReceiver", "Timer state cleared from SharedPreferences.")
        } catch (e: Exception) {
            Log.e("TimerAlarmReceiver", "Failed to clear SharedPreferences", e)
        }
    }
}