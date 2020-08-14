package com.corp.luqman.movielisting.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "tv_series_table")
@JsonClass(generateAdapter = true)
data class TvSeries(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Json(name = "id")
    var id : Long = 0,
    @ColumnInfo(name = "title")
    @Json(name = "title")
    var title : String? = "",
    @ColumnInfo(name = "img_url")
    @Json(name = "img_url")
    var img_url : String? = "",
    @ColumnInfo(name = "year")
    @Json(name = "year")
    var year : String? = "",
    @ColumnInfo(name = "sinopsis")
    @Json(name = "sinopsis")
    var sinopsis : String? = ""
)