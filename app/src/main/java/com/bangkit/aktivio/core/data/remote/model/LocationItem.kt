package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class LocationItem(
    @field:SerializedName("latitude")
    val latitude: Double,
    @field:SerializedName("longitude")
    val longitude: Double
)
