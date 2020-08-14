package com.corp.luqman.movielisting.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.corp.luqman.movielisting.data.models.TvSeries
import com.corp.luqman.movielisting.data.models.TvSeriesDetail

@Dao
interface TvSeriesDao {

    @Insert
    fun insertTvSeries(tvSeries: TvSeries)

    @Insert
    fun insertDetailTvSeries(detail: TvSeriesDetail)

    @Query("DELETE FROM tv_series_table")
    fun clearAllTvSeries()

    @Query("DELETE FROM detail_tv_series_table")
    fun clearAllDetailTvSeries()

    @Query("SELECT * FROM tv_series_table ORDER BY id")
    fun getAllTvSeries(): LiveData<MutableList<TvSeries>>

    @Query("SELECT * FROM detail_tv_series_table WHERE id_tv_series = :idTvSeries")
    fun getDetailTvSeries(idTvSeries : Long): TvSeriesDetail?

}