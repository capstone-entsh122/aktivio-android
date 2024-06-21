package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class NutritionItem(
    @field:SerializedName("bmr")
    val bmr: Number? = null,

    @field:SerializedName("kaloriHarian")
    val kaloriHarian: Number? = null,

    @field:SerializedName("proteinMin")
    val proteinMin: Number? = null,

    @field:SerializedName("proteinMax")
    val proteinMax: Number? = null,

    @field:SerializedName("lemakMin")
    val lemakMin: Number? = null,

    @field:SerializedName("lemakMax")
    val lemakMax: Number? = null,

    @field:SerializedName("karbohidratMin")
    val karbohidratMin: Number? = null,

    @field:SerializedName("karbohidratMax")
    val karbohidratMax: Number? = null,

    @field:SerializedName("air")
    val air: Number? = null
)
