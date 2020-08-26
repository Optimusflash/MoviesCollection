package com.optimus.moviescollection.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.optimus.moviescollection.data.model.MovieResponse
import com.optimus.moviescollection.data.repositories.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private lateinit var _popularMovies: LiveData<MovieResponse>
    private var page = 1

    val popularMovies: LiveData<MovieResponse>
        get() = _popularMovies

    init {
        viewModelScope.launch {
            _popularMovies = liveData {
                emit(repository.loadPopularMovies(page))
            }
        }
    }
}