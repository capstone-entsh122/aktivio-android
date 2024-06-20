package com.bangkit.aktivio.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel (
    val id: Int,
    val title: String,
    val author: String,
    val createdAt: String,
    val content: String,
    val thumbnail: Int,
) : Parcelable