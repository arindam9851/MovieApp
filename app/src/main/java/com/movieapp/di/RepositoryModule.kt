package com.movieapp.di

import com.movieapp.data.api.ApiInterface
import com.movieapp.data.api.NetworkMapper
import com.movieapp.data.repository.MainRepository
import com.movieapp.room.CacheMapper
import com.movieapp.room.TrendingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        trendingDao: TrendingDao,
        apiInterface: ApiInterface,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ):MainRepository{
        return MainRepository(trendingDao,apiInterface,cacheMapper,networkMapper)
    }
}