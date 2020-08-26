package com.optimus.moviescollection.data.repositories

import com.optimus.moviescollection.data.model.MovieResponse
import com.optimus.moviescollection.data.remote.MovieService
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainRepository @Inject constructor(private val api: MovieService) {

    suspend fun loadPopularMovies(page: Int): MovieResponse {
        return api.getPopularMovies(page)
    }
}