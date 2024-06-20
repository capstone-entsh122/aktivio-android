package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName


data class FoodNutritionItem (
    @field:SerializedName("label")
    val label: String,

    @field:SerializedName("nutrition")
    val nutrition: Nutrition? = null
)


data class Nutrition(
    @field:SerializedName("Porsi")
    val Porsi: String,

    @field:SerializedName("Kalori")
    val Kalori: String,

    @field:SerializedName("Protein")
    val Protein: String,

    @field:SerializedName("Lemak")
    val Lemak: String,

    @field:SerializedName("Karbohidrat")
    val Karbohidrat: String,

    @field:SerializedName("Serat")
    val Serat: String
)