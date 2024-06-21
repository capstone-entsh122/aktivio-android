package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class PlanItem(
    @field:SerializedName("id")
    val id: String?,

    @field:SerializedName("sportType")
    val sportType: String?,

    @field:SerializedName("recommendationDuration")
    val recommendationDuration: Number?,

    @field:SerializedName("totalElapsedTime")
    val totalElapsedTime: Number?,

    @field:SerializedName("steps")
    val steps: List<StepItem>?,
)
