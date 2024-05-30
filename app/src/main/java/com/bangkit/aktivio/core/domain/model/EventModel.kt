package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class EventModel(
    val name: String?,
    val description: String?,
    val creator: String?,
    val communities: String?,
    val startTime: String?,
    val endTime: String?,
    val participants: String?,
    val points: Int?,
) : Parcelable
