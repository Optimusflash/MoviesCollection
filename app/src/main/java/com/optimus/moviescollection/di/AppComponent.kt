package com.optimus.moviescollection.di

import com.optimus.moviescollection.data.paging.MovieDataSourceFactory
import com.optimus.moviescollection.di.modules.RemoteModule
import com.optimus.moviescollection.di.modules.ViewModelModule
import com.optimus.moviescollection.presentation.details.fragments.DetailsFragment
import com.optimus.moviescollection.presentation.home.fragments.HomeFragment
import com.optimus.moviescollection.presentation.popular.fragments.PopularFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */
@Singleton
@Component(modules = [RemoteModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(popularFragment: PopularFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(movieDataSourceFactory: MovieDataSourceFactory)
}