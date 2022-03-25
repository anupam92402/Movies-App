package com.example.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.model.movie.MovieResponse
import com.example.moviesapp.data.model.review.Reviews
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.utils.Constants

class DetailScreenRepository constructor(private val movieService: MovieService) {

    private val reviewsLiveData = MutableLiveData<Reviews>()
    private val similarMoviesLiveData = MutableLiveData<MovieResponse>()

    val reviews: LiveData<Reviews>
        get() = reviewsLiveData

    val similarMovies: LiveData<MovieResponse>
        get() = similarMoviesLiveData

    suspend fun getReviews(id: Int) {
        val result = movieService.getReviews(id, Constants.API_KEY)
        if (result.body() != null) {
            if (result.isSuccessful) {
                reviewsLiveData.postValue(result.body())
            }
        }
    }

    suspend fun getSimilarMovies(id: Int) {
        val result = movieService.getSimilarMovies(id, Constants.API_KEY)
        if (result.body() != null) {
            if (result.isSuccessful) {
                similarMoviesLiveData.postValue(result.body())
            }
        }
    }

}