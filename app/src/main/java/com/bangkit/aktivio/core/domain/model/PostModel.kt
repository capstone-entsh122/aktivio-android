package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModel(
    val postId: String?,
    val content: String?,
    val author: String?,
): Parcelable