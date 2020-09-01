package com.optimus.moviescollection.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.optimus.moviescollection.data.repositories.CreditsRepository
import com.optimus.moviescollection.data.repositories.DetailsRepository
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 31.08.2020.
 */
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository, private val creditsRepository: CreditsRepository) :
    ViewModel() {

    private val _movieId: MutableLiveData<Int> = MutableLiveData()


    val movieDetails= Transformations.switchMap(_movieId) {
            liveData {
                emit(detailsRepository.getDetails(it))
            }
        }

    val credits= Transformations.switchMap(_movieId) {
            liveData {
                emit(creditsRepository.loadCredits(it))
            }
        }


    fun setMovieId(id: Int?) {
        _movieId.value = id
    }
}