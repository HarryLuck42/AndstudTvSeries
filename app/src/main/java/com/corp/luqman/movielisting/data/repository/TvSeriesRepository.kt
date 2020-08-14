package com.corp.luqman.movielisting.data.repository

import androidx.lifecycle.LiveData
import com.corp.luqman.movielisting.data.database.TvSeriesDatabase
import com.corp.luqman.movielisting.data.models.TvSeries
import com.corp.luqman.movielisting.data.models.TvSeriesDetail
import com.corp.luqman.movielisting.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvSeriesRepository (private val apiService: ApiService, private val database : TvSeriesDatabase){

    suspend fun addTvSeries(value: TvSeries){
        withContext(Dispatchers.IO){
            database.tvDao.insertTvSeries(value)
        }
    }

    suspend fun addTvSeriesAll(values: MutableList<TvSeries>){
        withContext(Dispatchers.IO){
            for(value in values){
                addTvSeries(value)
            }
        }
    }

    suspend fun addTvSeriesDetail(value: TvSeriesDetail){
        withContext(Dispatchers.IO){
            database.tvDao.insertDetailTvSeries(value)
        }
    }

    suspend fun addTvSeriesDetailAll(details: MutableList<TvSeriesDetail>){
        withContext(Dispatchers.IO){
            for(value in details){
                addTvSeriesDetail(value)
            }
        }
    }

    suspend fun deleteAllTvSeriesDetail(){
        withContext(Dispatchers.IO){
            database.tvDao.clearAllDetailTvSeries()
        }
    }

    suspend fun deleteAllTvSeries(){
        withContext(Dispatchers.IO){
            database.tvDao.clearAllTvSeries()
        }
    }

    fun getAllTvSeries(): LiveData<MutableList<TvSeries>>?{
        val results = database.tvDao.getAllTvSeries()
        return results
    }

    suspend fun getDataDetailTvSeries(id : Long): TvSeriesDetail?{
        return withContext(Dispatchers.IO) {
            val result = database.tvDao.getDetailTvSeries(id)
            result
        }
    }

    fun getDataApiTvSeries() = apiService.getTvSeries()

    fun getDataApiTvSeriesDetail() = apiService.getDetailTvSeries()
}