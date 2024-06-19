package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecommendationItem(
    @field:SerializedName("preferences")
    val preferences: SurveyItem? = null,

    @field:SerializedName("sportPlan")
    val sportPlan: SportPlanItem? = null,

    @field:SerializedName("recommendedCaloriesNutritions")
    val recommendedCaloriesNutritions: NutritionItem? = null
)
