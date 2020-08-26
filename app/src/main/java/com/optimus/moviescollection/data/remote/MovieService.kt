package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.BuildConfig
import com.optimus.moviescollection.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */


interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page")page: Int): MovieResponse
}