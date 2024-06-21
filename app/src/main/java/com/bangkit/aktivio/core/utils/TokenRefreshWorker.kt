package com.bangkit.aktivio.core.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bangkit.aktivio.core.data.local.source.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenRefreshWorker @Inject constructor(private val context: Context, params: WorkerParameters, private val userPreferencesRepository: UserPreferencesRepository) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val tokenManager = TokenManager(context, userPreferencesRepository)
        return withContext(Dispatchers.IO) {
            try {
                tokenManager.refreshToken()
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}
