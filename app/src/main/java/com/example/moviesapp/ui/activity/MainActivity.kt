package com.example.moviesapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.data.model.movie.MovieResult
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.factory.MovieViewModelFactory
import com.example.moviesapp.ui.adapter.ViewPagerAdapter
import com.example.moviesapp.ui.adapter.rv_upcoming_adapter
import com.example.moviesapp.ui.viewmodel.MovieViewModel
import com.example.moviesapp.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), rv_upcoming_adapter.onMovieClickListener {

    private lateinit var binding: ActivityMainBinding
    private val titleArray = listOf("Upcoming", "Popular", "Top-Rated")
    private lateinit var viewModel: MovieViewModel//view model
    private lateinit var adapter: rv_upcoming_adapter

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

        adapter = rv_upcoming_adapter(this, this)
        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        setupSearchView()

        val movieApiService = Constants.getInstance()
            .create(MovieService::class.java)

        val repository = MovieRepository(movieApiService)

        viewModel =
            ViewModelProvider(this, MovieViewModelFactory(repository))
                .get(MovieViewModel::class.java)

        viewModel.searchMovie.observe(this, {
            adapter.setMovieList(it.results)
        })
    }

    private fun setupSearchView() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.rvMovies.visibility = View.VISIBLE
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
    }

    override fun onMovieClick(movieResult: MovieResult) {
        val intent = Intent(this, DetailScreenActivity::class.java)
        binding.rvMovies.visibility = View.GONE
        intent.putExtra("movie", movieResult)
        startActivity(intent)
    }

}