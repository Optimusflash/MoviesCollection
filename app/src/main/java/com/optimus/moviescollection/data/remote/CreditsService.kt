package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.data.model.credits.CastResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */
interface CreditsService {
    @GET("movie/{movieId}/credits")
    suspend fun getCredits(@Path("movieId") movieId: Int): CastResponse
}