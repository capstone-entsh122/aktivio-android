package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class CommunityItem(
    @field:SerializedName("communitiesId")
    val communitiesId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("creator")
    val creator: String,

    @field:SerializedName("members")
    val members: String,

    @field:SerializedName("posts")
    val posts: String,

    @field:SerializedName("events")
    val events: String
)
