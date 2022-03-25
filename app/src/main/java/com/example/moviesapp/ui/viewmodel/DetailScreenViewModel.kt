package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.movie.MovieResponse
import com.example.moviesapp.data.model.review.Reviews
import com.example.moviesapp.data.repository.DetailScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailScreenViewModel constructor(private val repository: DetailScreenRepository) :
    ViewModel() {

    fun getReviewsAndSimilarMovies(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getReviews(id)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSimilarMovies(id)
        }
    }

    val reviews: LiveData<Reviews>
        get() = repository.reviews

    val similarMovies: LiveData<MovieResponse>
        get() = repository.similarMovies
}