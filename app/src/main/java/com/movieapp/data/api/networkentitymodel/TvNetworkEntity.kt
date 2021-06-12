package com.movieapp.data.api.networkentitymodel


import com.google.gson.annotations.SerializedName

data class TvNetworkEntity(
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<ResultNetworkEntity> = listOf(),
    @SerializedName("total_pages")
    var totalPages: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0
)