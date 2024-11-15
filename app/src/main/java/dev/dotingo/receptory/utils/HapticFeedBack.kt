package dev.dotingo.receptory.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

@Suppress("DEPRECATION")
fun clickVibration(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    if (vibrator != null && vibrator.hasVibrator()) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
        } else {
            vibrator.vibrate(50)
        }
    }
}

@SuppressLint("NewApi")
@Suppress("DEPRECATION")
fun repeatingVibration(context: Context) {

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    vibrator.vibrate(
        VibrationEffect.createWaveform(
            longArrayOf(0, 500, 300),
            1
        )
    )
    vibrator.cancel()
}