package com.corp.luqman.movielisting.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episode (
    @Json(name = "episode")
    var episode : Int = 0,
    @Json(name = "title")
    var title : String? = "",
    @Json(name = "duration")
    var duration : String? = ""
)