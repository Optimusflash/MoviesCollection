package com.optimus.moviescollection.data.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.optimus.moviescollection.data.model.movie.Movie
import com.optimus.moviescollection.data.remote.PopularMovieService
import com.optimus.moviescollection.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */
private const val FIRST_PAGE = 1

class MovieDataSource (private val genreId:Int?, private val popularMovieService: PopularMovieService, private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Movie>() {

    var state: MutableLiveData<State> = MutableLiveData()

    private lateinit var retryBlock: () -> Unit

    companion object{
        const val PAGE_SIZE = 10
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        scope.launch {
            try {
                updateState(State.LOADING)
                val movieResponse = popularMovieService.getPopularMovies(FIRST_PAGE,genreId)
                callback.onResult(movieResponse.movies, null, FIRST_PAGE + 1)
                updateState(State.DONE)
            } catch (e: Exception) {
                updateState(State.ERROR)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                updateState(State.LOADING)
                val movieResponse = popularMovieService.getPopularMovies(params.key,genreId)
                val key = params.key + 1
                callback.onResult(movieResponse.movies, key)
                updateState(State.DONE)
            } catch (e: Exception) {
                updateState(State.ERROR)
                retryBlock = { loadAfter(params, callback) }
            }
        }
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        //empty
    }

    fun retry() {
        CoroutineScope(Dispatchers.IO).launch {
            retryBlock.invoke()
        }
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}