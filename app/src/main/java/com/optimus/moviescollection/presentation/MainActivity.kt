package com.optimus.moviescollection.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.optimus.moviescollection.R
import com.optimus.moviescollection.extensions.setTransparentLightStatusBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentLightStatusBar()
        setContentView(R.layout.activity_main)
    }








}