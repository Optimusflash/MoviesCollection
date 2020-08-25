package com.optimus.moviescollection.di

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */

class Injector{

    companion object{
        private lateinit var appComponent: AppComponent

        fun createDaggerComponent(){
            appComponent = DaggerAppComponent.create()
        }

        fun getAppComponent() = appComponent
    }
}