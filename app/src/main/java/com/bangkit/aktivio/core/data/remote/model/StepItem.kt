package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class StepItem(
    @field:SerializedName("order")
    val order: Number? = null,

    @field:SerializedName("instruction")
    val instruction: String?,

    @field:SerializedName("duration")
    val duration: Number?,

    @field:SerializedName("elapsedTime")
    val elapsedTime: Number?
)
