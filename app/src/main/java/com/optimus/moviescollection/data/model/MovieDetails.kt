package com.optimus.moviescollection.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 31.08.2020.
 */
data class MovieDetails(
    val id: Int,
    @SerializedName("backdrop_path")
    val imageUrl: String,
    @SerializedName("vote_average")
    val rating: Float,
    val popularity: Float,
    val title: String,
    val overview: String,
    val genres: List<Genre>,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("runtime")
    val duration: Int?,
    @SerializedName("vote_count")
    val voteCount : Int
) {
}

