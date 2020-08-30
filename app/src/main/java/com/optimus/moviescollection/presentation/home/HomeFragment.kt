package com.optimus.moviescollection.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.optimus.moviescollection.R
import com.optimus.moviescollection.databinding.FragmentHomeBinding
import com.optimus.moviescollection.presentation.boxoffice.BoxOfficeFragment
import com.optimus.moviescollection.presentation.comingsoon.ComingSoonFragment
import com.optimus.moviescollection.presentation.popular.PopularFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        initViews()

    }

    private fun initViews() {
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