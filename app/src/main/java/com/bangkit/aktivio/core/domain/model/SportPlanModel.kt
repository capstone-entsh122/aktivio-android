package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SportPlanModel(
    val sportsRecommendations: List<String>? = null,

    val timeRecommendations: String? = null,

    val weeklyRecommendations: String? = null,

    val plans: List<StepModel>? = null
) : Parcelable
