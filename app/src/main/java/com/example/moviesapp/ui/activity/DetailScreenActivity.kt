package com.example.moviesapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesapp.data.model.movie.MovieResult
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.data.repository.DetailScreenRepository
import com.example.moviesapp.databinding.ActivityDetailScreenBinding
import com.example.moviesapp.factory.DetailScreenViewModelFactory
import com.example.moviesapp.ui.adapter.rv_review_adapter
import com.example.moviesapp.ui.adapter.rv_similar_adapter
import com.example.moviesapp.ui.viewmodel.DetailScreenViewModel
import com.example.moviesapp.utils.Constants

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding
    private lateinit var viewModel: DetailScreenViewModel//view model
    private lateinit var reviewAdapter: rv_review_adapter
    private lateinit var similarMoiveAdapter: rv_similar_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movie") as MovieResult
        Toast.makeText(this, movie.title, Toast.LENGTH_LONG).show()

        reviewAdapter = rv_review_adapter(this)
        binding.rvReviews.adapter = reviewAdapter
        binding.rvReviews.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        similarMoiveAdapter = rv_similar_adapter(this)
        binding.rvSimilarMovies.adapter = similarMoiveAdapter
        binding.rvSimilarMovies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val movieApiService = Constants.getInstance()
            .create(MovieService::class.java)

        val repository = DetailScreenRepository(movieApiService)

        viewModel =
            ViewModelProvider(this, DetailScreenViewModelFactory(repository))
                .get(DetailScreenViewModel::class.java)

        viewModel.getReviewsAndSimilarMovies(movie.id)

        viewModel.reviews.observe(this, {
            Log.d("comments", it.results.toString())
            reviewAdapter.setReviewList(it.results)
        })

        viewModel.similarMovies.observe(this, {
            Log.d("similar movies", it.results[0].original_title)
            similarMoiveAdapter.setSimilarMovieList(it.results)
        })

        initializeData(movie)
    }

    private fun initializeData(movie: MovieResult) {
        binding.tvTitle.text = movie.title
        binding.tvRating.text = movie.popularity.toString()
        binding.tvLanguage.text = movie.original_language
        binding.tvDate.text = movie.release_date
        binding.tvVotes.text = movie.vote_average.toString() + "Votes"
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
            .into(binding.ivPoster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path)
            .into(binding.ivBackdropPath)
        binding.tvOverview.text = movie.overview
        binding.tvOverview.setOnClickListener {
            binding.tvOverview.maxLines = 25
        }
    }
}