package com.movieapp.data.repository

import com.movieapp.data.api.ApiInterface
import com.movieapp.data.api.NetworkMapper
import com.movieapp.data.api.networkentitymodel.TvNetworkEntity
import com.movieapp.domainmodel.Trending
import com.movieapp.room.CacheMapper
import com.movieapp.room.TrendingDao
import com.movieapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(

    private val trendingDao: TrendingDao,
    private val apiInterface: ApiInterface,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper

)

{

    suspend fun getTrending():Flow<DataState<List<Trending>>> = flow {
        emit(DataState.Loading)
        try {
            val networkResponse= apiInterface.getTrending("3a7ea0f56514e3a2aee56b40f2008c73")
            val trendingData= networkMapper.mapFromEntityList(networkResponse.results)
            for ( trending in trendingData ){
                trendingDao.insert(cacheMapper.mapToEntity(trending))
            }
            val cachedData= trendingDao.getTrending()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedData)))
        }
        catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

   suspend fun getCacheData(): Flow<DataState<List<Trending>>> = flow {
       emit(DataState.Loading)
       try {
           val cachedData= trendingDao.getTrending()
           emit(DataState.Success(cacheMapper.mapFromEntityList(cachedData)))
       }catch (e:Exception){
           emit(DataState.Error(e))

       }
   }
}