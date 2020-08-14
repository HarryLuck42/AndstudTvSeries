package com.corp.luqman.movielisting.data.remote.response

import com.corp.luqman.movielisting.data.models.TvSeries
import com.corp.luqman.movielisting.data.models.TvSeriesDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailTvSeriesResponse(
    @Json(name = "tv_series_detail")
    var tv_series_detail: MutableList<TvSeriesDetail>?
)