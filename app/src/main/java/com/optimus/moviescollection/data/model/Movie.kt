package com.optimus.moviescollection.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */

data class Movie(
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val title: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("release_date")
    val date: String?
) {

}