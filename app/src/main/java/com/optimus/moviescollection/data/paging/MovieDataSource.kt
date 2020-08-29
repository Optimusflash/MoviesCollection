package com.optimus.moviescollection.data.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.data.remote.PopularMovieService
import com.optimus.moviescollection.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */
private const val FIRST_PAGE = 1
private const val PAGE_MAX = 100

class MovieDataSource (private val genreId:Int?, private val popularMovieService: PopularMovieService) :
    PageKeyedDataSource<Int, Movie>() {


    var state: MutableLiveData<State> = MutableLiveData()

    private lateinit var retryBlock: () -> Unit


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        GlobalScope.launch {
            try {
                updateState(State.LOADING)
                val movieResponse = popularMovieService.getPopularMovies(FIRST_PAGE,genreId)
                Log.e("M_MovieDataSource", "loadInitial $movieResponse")
                callback.onResult(movieResponse.movies, null, FIRST_PAGE + 1)
                updateState(State.DONE)
            } catch (e: Exception) {
                Log.e("M_MovieDataSource", "${e.message}")
                updateState(State.ERROR)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        if (params.key == PAGE_MAX + 1) return
        GlobalScope.launch {
            try {
                updateState(State.LOADING)
                val movieResponse = popularMovieService.getPopularMovies(params.key,genreId)
                Log.e("M_MovieDataSource", "loadAfter $movieResponse")
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