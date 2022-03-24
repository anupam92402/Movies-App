package com.example.moviesapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.ui.adapter.ViewPagerAdapter
import com.example.moviesapp.ui.viewmodel.MovieViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val titleArray = listOf("Upcoming", "Popular", "Top-Rated")
    private lateinit var viewModel: MovieViewModel//view model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //view pager and tab layout
        val pagerAdapter = ViewPagerAdapter(this, titleArray)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleArray[position]
        }.attach()

//        val movieApiService = Constants.getInstance()
//            .create(MovieService::class.java)
//
//        val repository = MovieRepository(movieApiService)
//
//        viewModel =
//            ViewModelProvider(this, MovieViewModelFactory(repository))
//                .get(MovieViewModel::class.java)
//
//        viewModel.popularMovie.observe(this,{
//            Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
//        })
    }
}