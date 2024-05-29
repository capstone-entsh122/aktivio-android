package com.bangkit.aktivio.core.data.local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserPreferencesRepository (private val userDataStorePreferences: DataStore<Preferences>)  {
    suspend fun setToken(token: String) {
        Result.runCatching {
            userDataStorePreferences.edit { pref ->
                pref[KEY_TOKEN] = token
            }
        }
    }

    suspend fun deleteToken() {
        Result.runCatching {
            userDataStorePreferences.edit { pref ->
                pref.remove(KEY_TOKEN)
            }
        }
    }

    suspend fun getToken(): Result<String> {
        return Result.runCatching {
            val flow = userDataStorePreferences.data
                .catch { exception ->
                    if (exception is Exception) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[KEY_TOKEN]
                }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }

    suspend fun setSession(session: Boolean) {
        Result.runCatching {
            userDataStorePreferences.edit { pref ->
                pref[KEY_SESSION] = session
            }
        }
    }

    suspend fun getSession(): Result<Boolean> {
        return Result.runCatching {
            val flow = userDataStorePreferences.data
                .catch { exception ->
                    if (exception is Exception) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[KEY_SESSION]
                }
            val value = flow.firstOrNull() ?: false
            value
        }
    }

    suspend fun deleteSession() {
        Result.runCatching {
            userDataStorePreferences.edit { pref ->
                pref.remove(KEY_SESSION)
            }
        }
    }


    private companion object {

        val KEY_TOKEN = stringPreferencesKey(
            name = "token"
        )
        val KEY_SESSION = booleanPreferencesKey(
            name = "session"
        )
    }
}
