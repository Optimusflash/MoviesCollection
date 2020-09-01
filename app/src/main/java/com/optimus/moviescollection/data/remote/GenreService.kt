package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.data.model.GenreResponse
import retrofit2.http.GET

/**
 * Created by Dmitriy Chebotar on 31.08.2020.
 */
interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenreList(): GenreResponse
}