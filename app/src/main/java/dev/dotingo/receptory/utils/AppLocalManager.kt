package dev.dotingo.receptory.utils

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

data class Language(
    val code: String,
    val displayLanguage: String
)

val appLanguages = listOf(
    Language("ru", "Русский"),
    Language("en", "English")
)

class AppLocaleManager {

    fun getLanguageCode(context: Context,): String {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                ?.applicationLocales
                ?.get(0)
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)
        }
        return locale?.language ?: getDefaultLanguageCode()
    }

    private fun getDefaultLanguageCode(): String {
        return  appLanguages.first().code
    }
}