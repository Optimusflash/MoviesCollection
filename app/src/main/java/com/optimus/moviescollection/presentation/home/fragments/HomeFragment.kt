package com.optimus.moviescollection.presentation.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.optimus.moviescollection.R
import com.optimus.moviescollection.databinding.FragmentHomeBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import com.optimus.moviescollection.extensions.showToast
import com.optimus.moviescollection.presentation.boxoffice.BoxOfficeFragment
import com.optimus.moviescollection.presentation.comingsoon.ComingSoonFragment
import com.optimus.moviescollection.presentation.home.viewmodel.HomeSharedViewModel
import com.optimus.moviescollection.presentation.home.adapters.HomeViewPagerAdapter
import com.optimus.moviescollection.presentation.popular.fragments.PopularFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var homeSharedViewModel: HomeSharedViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDaggerComponent()
        initViews()
        initViewModel()
        setObservers()
    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModel() {
        homeSharedViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(HomeSharedViewModel::class.java)
    }

    private fun setObservers() {
        homeSharedViewModel.movieId.observe(viewLifecycleOwner, Observer {
            it?:return@Observer
            homeSharedViewModel.setMovieId(null)
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        })
    }

    private fun initViews() {
        binding.ivMenu.setOnClickListener { showToast() }
        binding.ivSearch.setOnClickListener { showToast() }
        tabLayout.setSelectedTabIndicator(R.drawable.tab_indicator)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager)
        viewPager.adapter = homeViewPagerAdapter
        viewPager.offscreenPageLimit = 3
        tabLayout.setupWithViewPager(viewPager)
        homeViewPagerAdapter.setupFragmentList(
            listOf(
                PopularFragment(),
                ComingSoonFragment(),
                BoxOfficeFragment()
            )
        )
    }
}