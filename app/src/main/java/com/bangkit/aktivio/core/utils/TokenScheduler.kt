package com.bangkit.aktivio.core.utils

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class TokenScheduler (private val context: Context) {

    fun scheduleTokenRefresh() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(false)
            .build()

        val refreshRequest = PeriodicWorkRequestBuilder<TokenRefreshWorker>(45, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "TokenRefreshWork",
            ExistingPeriodicWorkPolicy.REPLACE,
            refreshRequest
        )
    }
}
