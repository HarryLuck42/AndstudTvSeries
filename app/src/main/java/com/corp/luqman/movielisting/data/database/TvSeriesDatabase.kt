package com.corp.luqman.movielisting.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.corp.luqman.movielisting.data.models.TvSeries
import com.corp.luqman.movielisting.data.models.TvSeriesDetail

@Database(entities = [TvSeries::class, TvSeriesDetail::class], version = 1, exportSchema = false)
abstract class TvSeriesDatabase : RoomDatabase(){

    abstract val tvDao : TvSeriesDao

    companion object{
        @Volatile private var INSTANCE: TvSeriesDatabase? = null

        fun getInstance(context: Context): TvSeriesDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TvSeriesDatabase::class.java,
                        "tv_series_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}