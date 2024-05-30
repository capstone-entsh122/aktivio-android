package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepModel (
    val stepNum: Int?,
    val instruction: String?,
): Parcelable