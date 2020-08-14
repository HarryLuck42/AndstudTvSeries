package com.corp.luqman.movielisting.data.remote

import com.corp.luqman.movielisting.data.remote.response.DetailTvSeriesResponse
import com.corp.luqman.movielisting.data.remote.response.TvSeriesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("tv_series_netflix.json")
    fun getTvSeries():Deferred<TvSeriesResponse>

    @GET("detail_tv_series_netflix.json")
    fun getDetailTvSeries():Deferred<DetailTvSeriesResponse>
}