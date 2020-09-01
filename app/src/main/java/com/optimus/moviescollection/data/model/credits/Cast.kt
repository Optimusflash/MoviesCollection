package com.optimus.moviescollection.data.model.credits

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */
data class Cast(
    val id: Int,
    val name: String,
    val gender: Int,
    val character: String,
    @SerializedName("profile_path")
    val imageUrl: String
) {

}