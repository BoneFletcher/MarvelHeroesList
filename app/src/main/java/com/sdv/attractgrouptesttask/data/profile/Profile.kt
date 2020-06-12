package com.sdv.attractgrouptesttask.data.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Profile(
    @Json(name = "description")
    val description: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "itemId")
    val itemId: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "time")
    val time: String
)