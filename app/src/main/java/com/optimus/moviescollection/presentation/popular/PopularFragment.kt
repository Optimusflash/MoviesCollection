package com.optimus.moviescollection.presentation.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.android.material.chip.Chip
import com.optimus.moviescollection.data.model.Genre
import com.optimus.moviescollection.databinding.FragmentPopularBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import com.optimus.moviescollection.extensions.addChips
import com.optimus.moviescollection.extensions.setLightStatusBar
import com.optimus.moviescollection.presentation.home.HomeSharedViewModel
import com.optimus.moviescollection.presentation.popular.adapter.MoviesPagingAdapter
import com.optimus.moviescollection.utils.OffsetItemDecoration
import com.optimus.moviescollection.utils.State
import javax.inject.Inject

class PopularFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var popularViewModel: PopularViewModel
    private lateinit var homeSharedViewModel: HomeSharedViewModel
    private lateinit var binding: FragmentPopularBinding
    private val mainAdapter by lazy {
        MoviesPagingAdapter(onMovieClickHandler, retryClickHandler)
    }

    private val retryClickHandler = { popularViewModel.retry() }

    private val onMovieClickHandler: (id: Int) -> Unit = { homeSharedViewModel.setMovieId(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setLightStatusBar(true)
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
        popularViewModel =
            ViewModelProvider(this, viewModelFactory).get(PopularViewModel::class.java)
        homeSharedViewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeSharedViewModel::class.java)
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


    private fun setObservers() {
        popularViewModel.genres.observe(viewLifecycleOwner, Observer {
            setupChipsGroup(it)
        })

        popularViewModel.moviePageList.observe(viewLifecycleOwner, Observer {
            mainAdapter.submitList(it)
        })

        popularViewModel.getState().observe(viewLifecycleOwner, Observer {
            if (!popularViewModel.listIsEmpty()) {
                mainAdapter.setState(it ?: State.DONE)
            }
        })

    }

    private fun setupChipsGroup(it: List<Genre>) {
        binding.chipGroup.addChips(
            requireContext(),
            listOf(Genre(id = 0, name = "All")),
            clickListener = null
        )
        binding.chipGroup.addChips(requireContext(), it, clickListener = null )
        binding.chipGroup.setOnCheckedChangeListener{_, id ->
            popularViewModel.setCheckedChipId(id)
        }
        binding.chipGroup.check(popularViewModel.checkedGenreId)
    }
}