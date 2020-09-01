package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.data.model.movie.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Dmitriy Chebotar on 31.08.2020.
 */
interface MovieDetailsService {
    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetails
}