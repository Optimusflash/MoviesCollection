package com.optimus.moviescollection.data.remote

import com.optimus.moviescollection.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MoviesInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url.newBuilder()                  //by default
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", Locale.getDefault().language)
            .build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}