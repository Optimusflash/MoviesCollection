package com.optimus.moviescollection.presentation.main

import androidx.lifecycle.*
import com.optimus.moviescollection.data.model.Genre
import com.optimus.moviescollection.data.model.GenreResponse
import com.optimus.moviescollection.data.paging.MovieDataSource
import com.optimus.moviescollection.data.repositories.MainRepository
import com.optimus.moviescollection.utils.State
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainViewModel @Inject constructor(private val repository: MainRepository) :ViewModel() {


    val moviePageList = repository.moviesPageList
    private val movieDataSource = repository.movieDataSource

    private lateinit var _genres: LiveData<GenreResponse>
    val genres: LiveData<GenreResponse>
        get() = _genres

    init {
        viewModelScope.launch {
            _genres = liveData {
                emit(repository.loadGenres())
            }
        }
    }

    fun getState(): LiveData<State> =
        Transformations.switchMap(movieDataSource, MovieDataSource::state)

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