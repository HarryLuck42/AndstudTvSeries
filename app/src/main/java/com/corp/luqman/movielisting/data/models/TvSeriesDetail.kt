package com.corp.luqman.movielisting.data.models

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class SeasonConverter{
    @TypeConverter
    fun stringToSeasons(json: String) : MutableList<Season>{
        val gson = Gson()
        val type = object : TypeToken<MutableList<Season>>(){}.type
        val seasons = gson.fromJson<MutableList<Season>>(json, type)
        return seasons
    }

    @TypeConverter
    fun seasonsToString(list: MutableList<Season>) : String{
        val gson = Gson()
        val type = object : TypeToken<MutableList<Season>>(){}.type
        val json = gson.toJson(list, type)
        return json
    }
}

@Entity(tableName = "detail_tv_series_table")
@TypeConverters(SeasonConverter::class)
@JsonClass(generateAdapter = true)
data class TvSeriesDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Json(name = "id")
    var id : Long = 0,
    @ColumnInfo(name = "creator")
    @Json(name = "creator")
    var creator : String? = "",
    @ColumnInfo(name = "cast")
    @Json(name = "cast")
    var cast : String? = "",
    @ColumnInfo(name = "genre")
    @Json(name = "genre")
    var genre : String? = "",
    @ColumnInfo(name = "id_tv_series")
    @Json(name = "id_tv_series")
    var id_tv_series : Long = 0,
    @ColumnInfo(name = "seasons")
    @Json(name = "seasons")
    var seasons : MutableList<Season>?
)