package com.bangkit.aktivio.core.utils

import android.content.Context
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class TokenManager(private val context: Context, private val userPreferencesRepository: UserPreferencesRepository) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun refreshToken() {
        try {
            val user = auth.currentUser ?: return
            val tokenResult: GetTokenResult = user.getIdToken(true).await()
            val newToken = tokenResult.token ?: return

            userPreferencesRepository.setToken(newToken)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getToken(): String? {
        return runBlocking {
            userPreferencesRepository.getToken().getOrNull()
        }
    }
}
