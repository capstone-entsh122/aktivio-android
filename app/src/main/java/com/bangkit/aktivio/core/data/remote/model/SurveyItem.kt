package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SurveyItem(
    @field:SerializedName("motivation")
    val motivation: String,

    @field:SerializedName("availableTime")
    val availableTime: Int,

    @field:SerializedName("fitnessLevel")
    val fitnessLevel: String,

    @field:SerializedName("placePreference")
    val placePreference: String,

    @field:SerializedName("socialPreference")
    val socialPreference: String,

    @field:SerializedName("diseaseHistory")
    val diseaseHistory: List<String>,

    @field:SerializedName("weight")
    val weight: Int?,

    @field:SerializedName("height")
    val height: Int?
)
