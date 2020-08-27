package com.optimus.moviescollection.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.optimus.moviescollection.databinding.ActivityMainBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import com.optimus.moviescollection.presentation.main.adapter.MoviesPagingAdapter
import com.optimus.moviescollection.utils.OffsetItemDecoration
import com.optimus.moviescollection.utils.State
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    //  private val mainAdapter by lazy { MainMoviesAdapter() }
    private val mainAdapter by lazy {
        MoviesPagingAdapter {
            mainViewModel.retry()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
        val pagerSnapHelper = LinearSnapHelper()
        pagerSnapHelper.attachToRecyclerView( binding.rvMain)
        binding.rvMain.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMain.adapter = mainAdapter
        binding.rvMain.addItemDecoration(OffsetItemDecoration(this))


    }

    private fun setObservers() {
//        mainViewModel.popularMovies.observe(this, { popularMovies ->
//            mainAdapter.updateData(popularMovies.movies)
//        })

        mainViewModel.moviePageList.observe(this, Observer {
            mainAdapter.submitList(it)
        })

        mainViewModel.getState().observe(this, Observer {
            if (!mainViewModel.listIsEmpty()) {
                mainAdapter.setState(it ?: State.DONE)
            }
        })
    }
}