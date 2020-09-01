package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.data.model.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */


interface PopularMovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page")page: Int, @Query("with_genres")genreId: Int?): MovieResponse
}