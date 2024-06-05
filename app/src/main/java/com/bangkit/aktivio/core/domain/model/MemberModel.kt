package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberModel(
    val userId: String,
    val email: String,
    val displayName: String,
    val joinedAt: String
) : Parcelable
