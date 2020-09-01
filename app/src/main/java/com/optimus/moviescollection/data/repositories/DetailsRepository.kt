package com.optimus.moviescollection.data.repositories

import com.optimus.moviescollection.data.remote.MovieDetailsService
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 31.08.2020.
 */
class DetailsRepository @Inject constructor(private val movieDetailsService: MovieDetailsService) {

    suspend fun getDetails(id: Int) = movieDetailsService.getMovieDetails(id)
}