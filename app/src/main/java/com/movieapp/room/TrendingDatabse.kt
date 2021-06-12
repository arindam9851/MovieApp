package com.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrendingCacheEntity::class],version = 1)
abstract class  TrendingDatabse:RoomDatabase(){
    abstract fun trendingDoa(): TrendingDao


    companion object{
        val DATABASE_NAME:String="trending_db"
    }
}