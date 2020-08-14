package com.corp.luqman.movielisting.data.remote.response

import com.corp.luqman.movielisting.data.models.TvSeries
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvSeriesResponse(
    @Json(name = "tv_series")
    var tv_series: MutableList<TvSeries>?
)