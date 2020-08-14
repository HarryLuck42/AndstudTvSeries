package com.corp.luqman.movielisting.di


import com.corp.luqman.movielisting.data.database.TvSeriesDatabase
import com.corp.luqman.movielisting.data.remote.ApiService
import com.corp.luqman.movielisting.data.remote.createWebService
import com.corp.luqman.movielisting.data.remote.provideOkHttpClient
import com.corp.luqman.movielisting.data.repository.TvSeriesRepository
import com.corp.luqman.movielisting.ui.fragment.TvSeriesDetailViewModel
import com.corp.luqman.movielisting.ui.fragment.TvSeriesViewModel
import com.corp.luqman.movielisting.utils.Const
import com.corp.luqman.movielisting.utils.rx.AppSchedulerProvider
import com.corp.luqman.movielisting.utils.rx.SchedulerProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { provideOkHttpClient(androidContext()) }
    single { createWebService<ApiService>(get(), Const.appUrl) }
    single { AppSchedulerProvider() as SchedulerProvider }
    single { TvSeriesDatabase.getInstance(androidContext())}
}

val dataModule = module {
    single { TvSeriesRepository(get(), get()) }
}

val viewModelModule = module {

    viewModel {
        TvSeriesDetailViewModel(
            get(),
            get()
        )
    }
    viewModel {
        TvSeriesViewModel(
            get(),
            get()
        )
    }
}

val myAppModule = listOf(
    appModule,
    dataModule,
    viewModelModule
)
