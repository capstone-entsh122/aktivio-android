package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class PlanItem(
    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("sportType")
    val sportType: String?,

    @field:SerializedName("difficultyLevel")
    val difficultyLevel: String?,

    @field:SerializedName("recommendationDuration")
    val recommendationDuration: Int?,

    @field:SerializedName("steps")
    val steps: List<StepItem>?,
)
