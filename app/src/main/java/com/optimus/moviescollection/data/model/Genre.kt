package com.optimus.moviescollection.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 27.08.2020.
 */


data class Genre(
    val id: Int,
    val name: String
)

data class Genres(
    val genres: List<Genres>
)