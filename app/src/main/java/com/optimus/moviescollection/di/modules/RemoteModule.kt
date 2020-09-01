package com.optimus.moviescollection.di.modules

import com.optimus.moviescollection.BuildConfig
import com.optimus.moviescollection.data.remote.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    @Singleton
    fun provideMoviesInterceptor() = MoviesInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(moviesInterceptor: MoviesInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(moviesInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providePopularMovieService(retrofit: Retrofit): PopularMovieService {
        return retrofit.create(PopularMovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit): GenreService {
        return retrofit.create(GenreService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit): MovieDetailsService {
        return retrofit.create(MovieDetailsService::class.java)
    }

    @Provides
    @Singleton
    fun provideCreditsService(retrofit: Retrofit): CreditsService {
        return retrofit.create(CreditsService::class.java)
    }
}