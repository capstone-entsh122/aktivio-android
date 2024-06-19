package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SurveyItem(
    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("age")
    val age: Number? = null,

    @field:SerializedName("equipment")
    val equipment: String? = null,

    @field:SerializedName("motivation")
    val motivation: List<String>? = null,

    @field:SerializedName("availableTime")
    val availableTime: String? = null,

    @field:SerializedName("fitnessLevel")
    val fitnessLevel: String? = null,

    @field:SerializedName("placePreference")
    val placePreference: String? = null,

    @field:SerializedName("socialPreference")
    val socialPreference: String? = null,

    @field:SerializedName("diseaseHistory")
    val diseaseHistory: List<String>? = null,

    @field:SerializedName("weight")
    val weight: Number? = null,

    @field:SerializedName("height")
    val height: Number? = null
)
