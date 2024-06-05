package com.bangkit.aktivio.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class EventItem (

    @field:SerializedName("eventId")
    val eventId : String? = null,

    @field:SerializedName("name")
    val name : String? = null,

    @field:SerializedName("description")
    val description : String? = null,

    @field:SerializedName("creator")
    val creator : String? = null,

    @field:SerializedName("communities")
    val communities : String? = null,

    @field:SerializedName("startTime")
    val startTime : String? = null,

    @field:SerializedName("endTime")
    val endTime : String? = null,

    @field:SerializedName("participants")
    val participants : String? = null,

    @field:SerializedName("points")
    val points : Int? = null

)
