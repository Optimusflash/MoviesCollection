package com.optimus.moviescollection.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */
data class MovieResponse(
   @SerializedName("results")
   val movies: List<Movie>
) {
}