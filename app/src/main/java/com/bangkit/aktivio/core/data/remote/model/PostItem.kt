package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class PostItem(
    @field:SerializedName("postId")
    val postId: String?,

    @field:SerializedName("content")
    val content: String?,

    @field:SerializedName("author")
    val author: String?,
)
