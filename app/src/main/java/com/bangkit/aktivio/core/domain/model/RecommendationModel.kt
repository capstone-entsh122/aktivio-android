package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationModel(
    val preferences: SurveyModel? = null,

    val sportPlan: SportPlanModel? = null,

    val recommendedCaloriesNutritions: NutritionModel? = null
) : Parcelable
