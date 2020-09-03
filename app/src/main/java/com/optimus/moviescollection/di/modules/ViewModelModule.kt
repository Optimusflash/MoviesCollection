package com.optimus.moviescollection.di.modules

import androidx.lifecycle.ViewModel
import com.optimus.moviescollection.di.ViewModelKey
import com.optimus.moviescollection.presentation.details.viewmodel.DetailsViewModel
import com.optimus.moviescollection.presentation.home.viewmodel.HomeSharedViewModel
import com.optimus.moviescollection.presentation.popular.viewmodel.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */

@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(PopularViewModel::class)
    abstract fun provideMainViewModel(popularViewModel: PopularViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DetailsViewModel::class)
    abstract fun provideDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(HomeSharedViewModel::class)
    abstract fun provideHomeSharedViewModel(homeSharedViewModel: HomeSharedViewModel): ViewModel
}