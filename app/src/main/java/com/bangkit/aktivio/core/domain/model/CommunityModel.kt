package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommunityModel(
    val communitiesId: String?,
    val name: String?,
    val description: String?,
    val members: Int?,
    val events: Int?,
    val creator: String?,
    val posts: String?
) :Parcelable
