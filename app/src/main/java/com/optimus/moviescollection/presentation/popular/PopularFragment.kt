package com.optimus.moviescollection.presentation.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.optimus.moviescollection.data.model.GenreResponse
import com.optimus.moviescollection.databinding.FragmentPopularBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import com.optimus.moviescollection.presentation.popular.adapter.MoviesPagingAdapter
import com.optimus.moviescollection.utils.OffsetItemDecoration
import com.optimus.moviescollection.utils.State
import java.util.*
import javax.inject.Inject

class PopularFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var popularViewModel: PopularViewModel
    private lateinit var binding : FragmentPopularBinding
    private val mainAdapter by lazy {
        MoviesPagingAdapter {
            popularViewModel.retry()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDaggerComponent()
        initViewModels()
        initViews()
        setObservers()
    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModels() {
        popularViewModel = ViewModelProvider(this, viewModelFactory).get(PopularViewModel::class.java)
    }

    private fun initViews() {

        val pagerSnapHelper = LinearSnapHelper()
        pagerSnapHelper.attachToRecyclerView( binding.rvMain)
        binding.rvMain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMain.adapter = mainAdapter
        binding.rvMain.addItemDecoration(OffsetItemDecoration(requireContext()))
        binding.rvMain.isNestedScrollingEnabled = false


    }
    private fun setupChipsGroup(genreResponse: GenreResponse) {
        val genres = genreResponse.genres
        val listener: (String) -> Unit = { }
        val defaultChip = Chip(requireContext()).apply {
            id = 0
            text = "All" //TODO
            isChecked = true
        }
        binding.chipGroup.addView(defaultChip)
        for (i in genres.indices){
            val chip = Chip(requireContext()).apply {
                id = genres[i].id
                text = genres[i].name.capitalize(Locale.getDefault())
            }
            binding.chipGroup.addView(chip)
        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            popularViewModel.setCheckedChipId(checkedId)
        }
    }

    private fun setObservers() {
        popularViewModel.moviePageList.observe(viewLifecycleOwner, Observer {
                        mainAdapter.submitList(it)
        })


        popularViewModel.getState().observe(viewLifecycleOwner, Observer {
            if (!popularViewModel.listIsEmpty()) {
                mainAdapter.setState(it ?: State.DONE)
            }
        })

        popularViewModel.genres.observe(viewLifecycleOwner, Observer {
            Log.e("M_MainActivity", "$it")
            setupChipsGroup(it)
        })
    }
}