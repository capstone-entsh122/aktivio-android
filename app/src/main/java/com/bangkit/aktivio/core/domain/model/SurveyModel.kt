package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurveyModel(
    val gender: String? = null,

    val age: Number? = null,

    val equipment: String? = null,

    val motivation: List<String>? = null,

    val availableTime: String? = null,

    val fitnessLevel: String? = null,

    val placePreference: String? = null,

    val socialPreference: String? = null,

    val diseaseHistory: List<String>? = null,

    val weight: Number? = null,

    val height: Number? = null
) : Parcelable
