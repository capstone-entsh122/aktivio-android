package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurveyModel(
    val gender: String? = null,

    val age: Int? = null,

    val location: String? = null,

    val equipment: String? = null,

    val motivation: String? = null,

    val availableTime: Int? = null,

    val fitnessLevel: String? = null,

    val placePreference: String? = null,

    val socialPreference: String? = null,

    val diseaseHistory: List<String>? = null,

    val weight: Int? = null,

    val height: Int? = null
) : Parcelable
