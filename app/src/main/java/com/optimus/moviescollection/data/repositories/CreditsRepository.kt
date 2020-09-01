package com.optimus.moviescollection.data.repositories

import com.optimus.moviescollection.data.remote.CreditsService
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */
class CreditsRepository @Inject constructor(private val creditsService: CreditsService) {
    suspend fun loadCredits(movieId: Int) = creditsService.getCredits(movieId)
}