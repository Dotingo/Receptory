package dev.dotingo.receptory.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "receptory_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreManager(private val context: Context) {

    suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_FIRST_LAUNCH_KEY] = isFirstLaunch
        }
    }

    fun isFirstLaunchFlow(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[IS_FIRST_LAUNCH_KEY] ?: true
            }
    }

    suspend fun setUserLoggedIn(isUserLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN_KEY] = isUserLoggedIn
        }
    }

    fun isUserLoggedIn(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[IS_USER_LOGGED_IN_KEY] ?: false
            }
    }

    companion object {
        private val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")
        private val IS_USER_LOGGED_IN_KEY = booleanPreferencesKey("is_user_logged_in")
    }
}
