package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.movie.MovieResponse
import com.example.moviesapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel constructor(private val repository: MovieRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUpcomingMovies()
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTopRatedMovies()
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPopularMovies()
        }
    }

    fun search(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchMovies(search)
        }
    }

    val upcomingMovie: LiveData<MovieResponse>
        get() = repository.upcomingMovies

    val popularMovie: LiveData<MovieResponse>
        get() = repository.popularMovies

    val topRatedMovie: LiveData<MovieResponse>
        get() = repository.topRatedMovies

    val searchMovie: LiveData<MovieResponse>
        get() = repository.searchMovies

}