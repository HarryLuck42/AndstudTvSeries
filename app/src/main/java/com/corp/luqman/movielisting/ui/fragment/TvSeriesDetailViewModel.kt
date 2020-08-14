package com.corp.luqman.movielisting.ui.fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.corp.luqman.movielisting.data.models.TvSeriesDetail
import com.corp.luqman.movielisting.data.repository.TvSeriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TvSeriesDetailViewModel (private val tvSeriesRepository: TvSeriesRepository, val context: Context) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _tvSeriesDetail = MutableLiveData<TvSeriesDetail>()
    val tvSeriesDetail : LiveData<TvSeriesDetail>
        get() = _tvSeriesDetail


    fun initializeDetail(id : Long){
        uiScope.launch {
            _tvSeriesDetail.value = tvSeriesRepository.getDataDetailTvSeries(id)
        }
    }


}