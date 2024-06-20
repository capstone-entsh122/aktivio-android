package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodNutritionModel (
    val label: String,
    val nutrition: Nutrition? = null
) : Parcelable

@Parcelize
data class Nutrition(
    val Porsi: String,
    val Kalori: String,
    val Protein: String,
    val Lemak: String,
    val Karbohidrat: String,
    val Serat: String
) : Parcelable