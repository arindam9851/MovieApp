package com.movieapp.data.api

import com.movieapp.data.api.networkentitymodel.TvNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("trending/tv/week")
    suspend fun getTrending(@Query("api_key")api_key: String): TvNetworkEntity
}