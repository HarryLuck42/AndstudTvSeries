package com.corp.luqman.movielisting

import android.app.Application
import com.corp.luqman.movielisting.di.myAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(myAppModule)
        }

    }
}