package com.movieapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending")
data class TrendingCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id:Int,

    @ColumnInfo(name = "originalName")
    var originalName:String,


    @ColumnInfo(name = "voteAvegare")
    var voteAvegare:Double,

    @ColumnInfo(name = "overview")
    var overview:String,


    @ColumnInfo(name = "voteCount")
    var voteCount:Int,

    @ColumnInfo(name = "imagepath")
    var imagepath:String


){

}
