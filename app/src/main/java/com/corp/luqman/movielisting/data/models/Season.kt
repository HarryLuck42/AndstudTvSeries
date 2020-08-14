package com.corp.luqman.movielisting.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "season")
    var season : Int = 0,
    @Json(name = "episodes")
    var episodes : MutableList<Episode>? = null
)