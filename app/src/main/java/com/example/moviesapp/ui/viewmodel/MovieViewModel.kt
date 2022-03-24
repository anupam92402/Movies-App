package com.example.moviesapp.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.model.movie.MovieResponse
import com.example.moviesapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    init {
        GlobalScope.launch(Dispatchers.IO) {
            repository.getUpcomingMovies()
        }
        GlobalScope.launch(Dispatchers.IO) {
            repository.getTopRatedMovies()
        }
        GlobalScope.launch(Dispatchers.IO) {
            repository.getPopularMovies()
        }
    }

    val upcomingMovie: LiveData<MovieResponse>
        get() = repository.upcomingMovies

    val popularMovie: LiveData<MovieResponse>
        get() = repository.popularMovies

    val topRatedMovie: LiveData<MovieResponse>
        get() = repository.topRatedMovies

}