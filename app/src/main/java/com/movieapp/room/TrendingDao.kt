package com.movieapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trendingEntity: TrendingCacheEntity):Long

    @Query("SELECT * FROM trending")
    suspend fun getTrending():List<TrendingCacheEntity>
}