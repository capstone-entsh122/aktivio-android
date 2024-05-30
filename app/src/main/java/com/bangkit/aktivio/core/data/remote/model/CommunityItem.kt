package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class CommunityItem(
    @field:SerializedName("communitiesId")
    val communitiesId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("creator")
    val creator: String? = null,

    @field:SerializedName("members")
    val members: String? = null,

    @field:SerializedName("posts")
    val posts: String? = null,

    @field:SerializedName("events")
    val events: String? = null
)
