package com.example.moviesapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesapp.ui.fragment.PopularMovieFragment
import com.example.moviesapp.ui.fragment.TopRatedMovieFragment
import com.example.moviesapp.ui.fragment.UpcomingMovieFragment

//view Pager Adapter to do swipe fragments in main activity

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val titleArray: List<String>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return titleArray.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UpcomingMovieFragment()
            1 -> PopularMovieFragment()
            2 -> TopRatedMovieFragment()
            else -> UpcomingMovieFragment()
        }
    }
}