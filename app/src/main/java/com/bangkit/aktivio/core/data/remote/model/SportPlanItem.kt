package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SportPlanItem(
    @field:SerializedName("sportType")
    val sportsRecommendations: List<String>? = null,

    @field:SerializedName("arrivalTimeRecommendations")
    val timeRecommendations: String? = null,

    @field:SerializedName("weeklyRecommendations")
    val weeklyRecommendations: String? = null,

    @field:SerializedName("plans")
    val plans: List<StepItem>? = null
)
