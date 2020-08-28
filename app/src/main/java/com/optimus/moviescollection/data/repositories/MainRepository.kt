package com.optimus.moviescollection.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.data.model.MovieResponse
import com.optimus.moviescollection.data.paging.MovieDataSource
import com.optimus.moviescollection.data.paging.MovieDataSourceFactory
import com.optimus.moviescollection.data.remote.GenreService
import com.optimus.moviescollection.data.remote.PopularMovieService
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainRepository @Inject constructor(private val popularMovieService: PopularMovieService, private val genreService: GenreService) {

    private val movieDataSourceFactory: MovieDataSourceFactory = MovieDataSourceFactory(popularMovieService)

    private val _moviesPageList: LiveData<PagedList<Movie>>
    val moviesPageList
        get() = _moviesPageList

    private val _movieDataSource = movieDataSourceFactory.movieLiveDataSource
    val movieDataSource:MutableLiveData<MovieDataSource>
        get() = _movieDataSource

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)//TODO
            .setEnablePlaceholders(false)
            .build()
        _moviesPageList = LivePagedListBuilder(movieDataSourceFactory, config).build()
    }

    suspend fun loadPopularMovies(page: Int): MovieResponse {
        return popularMovieService.getPopularMovies(page)
    }

    suspend fun loadGenres() = genreService.getGenreList()


}