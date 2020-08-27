package com.optimus.moviescollection.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.data.paging.MovieDataSource
import com.optimus.moviescollection.data.paging.MovieDataSourceFactory
import com.optimus.moviescollection.data.repositories.MainRepository
import com.optimus.moviescollection.utils.State
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainViewModel @Inject constructor(private val repository: MainRepository) :ViewModel() {


     val moviePageList = repository.moviesPageList
     private val movieDataSource = repository.movieDataSource

    fun getState(): LiveData<State> = Transformations.switchMap(movieDataSource, MovieDataSource::state)

    fun retry() {
        movieDataSource.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return moviePageList.value?.isEmpty() ?: true
    }

//    private lateinit var _popularMovies: LiveData<MovieResponse>
//    private var page = 1
//
//    val popularMovies: LiveData<MovieResponse>
//        get() = _popularMovies
//
//    init {
//        viewModelScope.launch {
//            _popularMovies = liveData {
//                emit(repository.loadPopularMovies(page))
//            }
//        }
//    }
}