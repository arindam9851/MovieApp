package com.movieapp.data.api.networkentitymodel


import com.google.gson.annotations.SerializedName

data class ResultNetworkEntity(
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @SerializedName("first_air_date")
    var firstAirDate: String = "",
    @SerializedName("genre_ids")
    var genreIds: List<Int> = listOf(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("media_type")
    var mediaType: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("origin_country")
    var originCountry: List<String> = listOf(),
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @SerializedName("original_name")
    var originalName: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int = 0
)