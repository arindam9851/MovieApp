package com.movieapp.di

import android.content.Context
import androidx.room.Room
import com.movieapp.room.TrendingDao
import com.movieapp.room.TrendingDatabse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {


    @Singleton
    @Provides
    fun provideTrendingDb(@ApplicationContext context: Context):TrendingDatabse{
        return Room.databaseBuilder(
            context,
            TrendingDatabse::class.java,
            TrendingDatabse.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTrendingDao(trendingDatabse: TrendingDatabse):TrendingDao{
        return trendingDatabse.trendingDoa()
    }


}