package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("error")
    val error: String?,

    @field:SerializedName("data")
    val data: Map<String, Any>?
)