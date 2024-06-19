package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class NutritionItem(
    @field:SerializedName("bmr")
    val bmr: Double? = null,

    @field:SerializedName("kaloriHarian")
    val kaloriHarian: Double? = null,

    @field:SerializedName("proteinMin")
    val proteinMin: Double? = null,

    @field:SerializedName("proteinMax")
    val proteinMax: Double? = null,

    @field:SerializedName("lemakMin")
    val lemakMin: Double? = null,

    @field:SerializedName("lemakMax")
    val lemakMax: Double? = null,

    @field:SerializedName("karbohidratMin")
    val karbohidratMin: Double? = null,

    @field:SerializedName("karbohidratMax")
    val karbohidratMax: Double? = null,

    @field:SerializedName("air")
    val air: Double? = null
)
