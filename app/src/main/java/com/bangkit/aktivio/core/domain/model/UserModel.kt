package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    val userId: String? = null,
    val email: String? = null,
    val displayName: String? = null,
    val gender: String? = null,
    val age: Int? = null,
    val password: String? = null,
    val equipment: String? = null,
    val location: String? = null,
    val preferences: SurveyModel? = null,
    val communities: List<String>? = null,
    val points: Int? = null,
    val token: String? = null
) : Parcelable