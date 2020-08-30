package com.optimus.moviescollection.presentation.popular

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.optimus.moviescollection.data.model.GenreResponse
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.data.paging.MovieDataSource
import com.optimus.moviescollection.data.paging.MovieDataSourceFactory
import com.optimus.moviescollection.data.repositories.MainRepository
import com.optimus.moviescollection.utils.State
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class PopularViewModel @Inject constructor(private val repository: MainRepository) :ViewModel() {

    private lateinit var _moviePageList: LiveData<PagedList<Movie>>
    val moviePageList: LiveData<PagedList<Movie>>
        get() = _moviePageList

    private lateinit var movieDataSourceFactory: MovieDataSourceFactory
    private lateinit var _movieDataSource: MutableLiveData<MovieDataSource>

    private lateinit var _genres: LiveData<GenreResponse>
    val genres: LiveData<GenreResponse>
        get() = _genres

    init {
        setupPagedListAndDataSource()
        viewModelScope.launch {
            _genres = liveData {
                emit(repository.loadGenres())
            }
        }
    }

    private fun setupPagedListAndDataSource() {
        movieDataSourceFactory = MovieDataSourceFactory(viewModelScope)
        val config = PagedList.Config.Builder()
            .setPageSize(10)//TODO
            .setEnablePlaceholders(false)
            .build()
        _moviePageList = LivePagedListBuilder(movieDataSourceFactory, config).build()
        _movieDataSource = movieDataSourceFactory.movieLiveDataSource
    }

    fun getState(): LiveData<State> =
        Transformations.switchMap(_movieDataSource, MovieDataSource::state)

    fun retry() {
        _movieDataSource.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return _moviePageList.value?.isEmpty() ?: true
    }

    fun setCheckedChipId(checkedId: Int) {
        val result = if (checkedId == 0) null else checkedId
        movieDataSourceFactory.genreId = result
        _movieDataSource.value?.invalidate()
    }

}