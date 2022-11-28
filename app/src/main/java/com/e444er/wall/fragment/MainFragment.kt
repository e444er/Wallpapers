package com.e444er.wall.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.e444er.wall.R
import com.e444er.wall.adapter.ViewPagerAdapter
import com.e444er.wall.databinding.MainFragmentBinding
import com.e444er.wall.util.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding()

    private val fragments =
        listOf(HomeFragment(), PopularFragment(), RandomFragment(), CategoriesFragment())

    private val tabTitles = listOf("Home", "Popular", "Random", "Categories")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }


    private fun initViewPager() {
        val pagerAdapter = ViewPagerAdapter(requireActivity(), fragments)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = false
    }

}