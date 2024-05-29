package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    val userId: String?,
    val email: String?,
    val displayName: String?,
    val gender: String?,
    val age: Int?,
    val equipment: String?,
    val location: String?,
    val preferences: SurveyModel?,
    val communities: List<String>?,
    val points: Int?
) : Parcelable