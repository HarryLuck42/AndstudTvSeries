package com.corp.luqman.movielisting.ui.fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.corp.luqman.movielisting.data.models.TvSeries
import com.corp.luqman.movielisting.data.models.TvSeriesDetail
import com.corp.luqman.movielisting.data.remote.response.DetailTvSeriesResponse
import com.corp.luqman.movielisting.data.remote.response.TvSeriesResponse
import com.corp.luqman.movielisting.data.repository.TvSeriesRepository
import com.corp.luqman.movielisting.utils.UiState
import kotlinx.coroutines.*
import java.lang.Exception

class TvSeriesViewModel (private val tvSeriesRepository: TvSeriesRepository, val context: Context) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val scope = CoroutineScope((GlobalScope.coroutineContext))
    val picsState = MutableLiveData<UiState<TvSeriesResponse>>()
    val picsStateDetail = MutableLiveData<UiState<DetailTvSeriesResponse>>()

    private val _tvValues = MutableLiveData<MutableList<TvSeries>>()
    val tvValues : LiveData<MutableList<TvSeries>>
        get() = _tvValues

    fun saveTVSeriesValues(values : MutableList<TvSeries>){
        uiScope.launch {
            tvSeriesRepository.deleteAllTvSeries()
            tvSeriesRepository.addTvSeriesAll(values)
        }
    }

    fun saveTVSeriesDetails(values: MutableList<TvSeriesDetail>){
        uiScope.launch {
            tvSeriesRepository.deleteAllTvSeriesDetail()
            tvSeriesRepository.addTvSeriesDetailAll(values)
        }
    }

    val getAllTvSeries = tvSeriesRepository.getAllTvSeries()

    fun getTvSeriesValuesAPI(){
        picsState.value = UiState.Loading()

        scope.launch {
            try {
                val result = tvSeriesRepository.getDataApiTvSeries().await()
                saveTVSeriesValues(result.tv_series!!)
                picsState.postValue(UiState.Success(result))
            }catch (e: Exception){
                picsState.postValue(UiState.Error(e))
            }
        }
    }

    fun getTvSeriesDetailAPI(){
        picsStateDetail.value = UiState.Loading()

        scope.launch {
            try {
                val result = tvSeriesRepository.getDataApiTvSeriesDetail().await()
                saveTVSeriesDetails(result.tv_series_detail!!)
                picsStateDetail.postValue(UiState.Success(result))
            }catch (e: Exception){
                picsStateDetail.postValue(UiState.Error(e))
            }
        }
    }

    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToDetail
        get() = _navigateToDetail

    fun onTvSeriesClicked(id: Long) {
        _navigateToDetail.value = id
    }

    fun onTvSeriesNavigated(){
        _navigateToDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        scope.launch {
            tvSeriesRepository.deleteAllTvSeries()
            tvSeriesRepository.deleteAllTvSeriesDetail()
        }

    }
}