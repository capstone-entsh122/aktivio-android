package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlanModel(
    val id: String?,
    val recommendedDuration: Number?,
    val sportType: String?,
    val totalElapsedTime: Number?,
    val steps: List<StepModel>?,
) : Parcelable
