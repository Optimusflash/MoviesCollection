package com.optimus.moviescollection.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.optimus.moviescollection.data.model.movie.Movie
import com.optimus.moviescollection.data.remote.PopularMovieService
import com.optimus.moviescollection.di.Injector
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */
class MovieDataSourceFactory (private val scope: CoroutineScope): DataSource.Factory<Int, Movie>() {
    @Inject
    lateinit var popularMovieService: PopularMovieService
    private val _movieLiveDataSource = MutableLiveData<MovieDataSource>()
    val movieLiveDataSource: MutableLiveData<MovieDataSource>
        get() = _movieLiveDataSource
    var genreId: Int? = null

    init {
        Injector.getAppComponent().inject(this)
    }

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(genreId, popularMovieService, scope)
        _movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}