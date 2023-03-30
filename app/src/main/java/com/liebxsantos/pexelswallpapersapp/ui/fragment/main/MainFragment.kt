package com.liebxsantos.pexelswallpapersapp.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.liebxsantos.pexelswallpapersapp.R
import com.liebxsantos.pexelswallpapersapp.databinding.FragmentMainBinding
import com.liebxsantos.pexelswallpapersapp.ui.fragment.categories.CategoriesFragment
import com.liebxsantos.pexelswallpapersapp.ui.fragment.collections.CollectionsFragment
import com.liebxsantos.pexelswallpapersapp.ui.fragment.popular.PopularFragment
import com.liebxsantos.pexelswallpapersapp.ui.pageradapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val tabTitles = listOf("Popular", "Collections", "Categories")
    private val fragments = listOf(PopularFragment(), CollectionsFragment(), CategoriesFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        initTabLayout()
        detail()
    }

    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initToolbar(){
        binding.toolbar.title = "Wallpapers"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager(){
        val pagerAdapter = ViewPagerAdapter(requireActivity(), fragments)
        binding.run {
            viewPager.adapter = pagerAdapter
        }
    }

    private fun detail() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_galleryFragment)
        }
    }
}