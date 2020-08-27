package com.optimus.moviescollection.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.data.remote.PopularMovieService


/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */
class MovieDataSourceFactory (private val popularMovieService: PopularMovieService): DataSource.Factory<Int, Movie>() {

    val movieLiveDataSource = MutableLiveData<MovieDataSource>()  //TODO

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(popularMovieService)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}