package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class StepItem(
    @field:SerializedName("stepNum")
    val stepNum: Int?,

    @field:SerializedName("instruction")
    val instruction: String?,
)
