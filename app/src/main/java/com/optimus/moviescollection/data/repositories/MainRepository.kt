package com.optimus.moviescollection.data.repositories

import com.optimus.moviescollection.data.remote.GenreService
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainRepository @Inject constructor(private val genreService: GenreService) {
    suspend fun loadGenres() = genreService.getGenreList().genres
}