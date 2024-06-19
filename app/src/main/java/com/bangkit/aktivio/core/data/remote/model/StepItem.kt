package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class StepItem(
    @field:SerializedName("order")
    val order: Int?,

    @field:SerializedName("instruction")
    val instruction: String?,
)
