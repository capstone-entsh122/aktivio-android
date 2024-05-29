package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserItem (
    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("equipment")
    val equipment: String? = null,

    @field:SerializedName("preferences")
    val preferences: SurveyItem? = null,

    @field:SerializedName("communities")
    val communities: List<String>? = null,

    @field:SerializedName("points")
    val points: Int? = null
)