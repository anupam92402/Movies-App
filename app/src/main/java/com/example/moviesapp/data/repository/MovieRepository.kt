package com.example.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.data.model.movie.MovieResponse
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.utils.Constants

class MovieRepository constructor(private val movieService: MovieService) {

    private val upcomingMovieLiveData = MutableLiveData<MovieResponse>()
    private val popularMovieLiveData = MutableLiveData<MovieResponse>()
    private val topRatedMovieLiveData = MutableLiveData<MovieResponse>()

    val upcomingMovies: LiveData<MovieResponse>
        get() = upcomingMovieLiveData

    val popularMovies: LiveData<MovieResponse>
        get() = popularMovieLiveData

    val topRatedMovies: LiveData<MovieResponse>
        get() = topRatedMovieLiveData

    suspend fun getPopularMovies() {
        val result = movieService.getPopularMovies(Constants.API_KEY, 1)
        if (result.body() != null) {
            if (result.isSuccessful) {
                popularMovieLiveData.postValue(result.body())
            }
        }
    }

    suspend fun getTopRatedMovies() {
        val result = movieService.getTopRatedMovies(Constants.API_KEY, 1)
        if (result.body() != null) {
            if (result.isSuccessful) {
                topRatedMovieLiveData.postValue(result.body())
            }
        }
    }

    suspend fun getUpcomingMovies() {
        val result = movieService.getUpcomingMovies(Constants.API_KEY, 1)
        if (result.body() != null) {
            if (result.isSuccessful) {
                upcomingMovieLiveData.postValue(result.body())
            }
        }
    }
}