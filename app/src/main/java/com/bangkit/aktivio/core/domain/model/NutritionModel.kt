package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutritionModel(
    val bmr: Double? = null,

    val kaloriHarian: Double? = null,

    val proteinMin: Double? = null,

    val proteinMax: Double? = null,

    val lemakMin: Double? = null,

    val lemakMax: Double? = null,

    val karbohidratMin: Double? = null,

    val karbohidratMax: Double? = null,

    val air: Double? = null
) : Parcelable
