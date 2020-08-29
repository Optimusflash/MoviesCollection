package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.data.model.Genre
import com.optimus.moviescollection.data.model.GenreResponse
import com.optimus.moviescollection.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */


interface PopularMovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page")page: Int, @Query("with_genres")genreId: Int?): MovieResponse
}

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenreList(): GenreResponse
}