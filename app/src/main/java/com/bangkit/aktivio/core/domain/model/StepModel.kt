package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepModel (
    val order: Number?,
    val instruction: String?,
    val duration: Number?,
    val elapsedTime: Number?
): Parcelable