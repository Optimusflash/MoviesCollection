package com.optimus.moviescollection.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.optimus.moviescollection.R
import com.optimus.moviescollection.data.remote.MovieService
import com.optimus.moviescollection.databinding.ActivityMainBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDaggerComponent()
        initViewModels()
        initViews()
        setObservers()
    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModels() {
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun initViews() {

    }

    private fun setObservers() {
        mainViewModel.popularMovies.observe(this, { popularMovies->
            var temp = ""
                popularMovies.movies.forEach {
                    temp += it.title +"\n"
                }
                binding.tvMain.text = temp
        })
    }

}