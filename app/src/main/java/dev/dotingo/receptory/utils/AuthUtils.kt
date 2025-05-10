package dev.dotingo.receptory.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dev.dotingo.receptory.R
import java.util.Locale

object AuthUtils {

    fun resetPassword(email: String, context: Context) {
        if (email.isNotEmpty()) {
            val languageCode = Locale.getDefault().language
            FirebaseAuth.getInstance().setLanguageCode(languageCode)
            Log.d("AuthUtils", "Язык: $languageCode")
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    val localizedContext = getLocalizedContext(context, languageCode)
                    Toast.makeText(
                        localizedContext,
                        localizedContext .getString(R.string.reset_password_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    val localizedContext = getLocalizedContext(context, languageCode)
                    Log.d("AuthUtils", "Ошибка: ${e.localizedMessage}")
                    Toast.makeText(
                        localizedContext,
                        localizedContext.getString(R.string.reset_password_error, e.localizedMessage ?: ""),
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            val languageCode = Locale.getDefault().language
            val localizedContext = getLocalizedContext(context, languageCode)
            Toast.makeText(
                localizedContext,
                localizedContext.getString(R.string.reset_password_empty_email),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

fun getLocalizedContext(context: Context, languageCode: String): Context {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = context.resources.configuration
    config.setLocale(locale)
    return context.createConfigurationContext(config)
}