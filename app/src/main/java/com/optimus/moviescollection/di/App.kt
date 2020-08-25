package com.optimus.moviescollection.di

import android.app.Application
import com.google.firebase.FirebaseApp


/**
 * Created by Dmitriy Chebotar on 24.08.2020.
 */
class App : Application() {
      override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this);
        Injector.createDaggerComponent()
    }


}