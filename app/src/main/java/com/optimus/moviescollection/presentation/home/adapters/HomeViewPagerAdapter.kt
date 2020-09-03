package com.optimus.moviescollection.presentation.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by Dmitriy Chebotar on 30.08.2020.
 */
class HomeViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val tabTitleList = listOf("Popular", "Coming soon", "Box office")

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun setupFragmentList(fragmentList: List<Fragment>){
        this.fragmentList.addAll(fragmentList)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitleList[position]
    }
}