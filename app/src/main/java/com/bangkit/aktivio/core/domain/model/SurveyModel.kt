package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurveyModel(
    val motivation: String,
) : Parcelable
