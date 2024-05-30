package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlanModel(
    val name: String?,
    val description: String?,
    val sportType: String?,
    val difficultyLevel: String?,
    val recommendationDuration: Int?,
    val steps: List<StepModel>?,
) : Parcelable
