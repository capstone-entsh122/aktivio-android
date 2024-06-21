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

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("location")
    val location: LocationItem? = null,

    @field:SerializedName("equipment")
    val equipment: String? = null,

    @field:SerializedName("preferences")
    val preferences: SurveyItem? = null,

    @field:SerializedName("communities")
    val communities: List<String>? = null,

    @field:SerializedName("points")
    val points: Number? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("joinedCommunities")
    val joinedCommunities: List<String>? = null,

    @field:SerializedName("dailyCalories")
    val dailyCalories: Number? = null,

    @field:SerializedName("createdAt")
    val createdAt: CreatedAt? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: UpdatedAt? = null,

    @field:SerializedName("recommendedCaloriesNutritions")
    val recommendedCaloriesNutritions: NutritionItem? = null,

    @field:SerializedName("sportPlan")
    val sportPlan: SportPlanItem? = null
)