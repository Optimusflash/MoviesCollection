package com.optimus.moviescollection.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 31.08.2020.
 */

class HomeSharedViewModel @Inject constructor() : ViewModel() {

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int>
        get() = _movieId

    fun setMovieId(id: Int?) {
        _movieId.value = id
    }
}