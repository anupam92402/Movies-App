package com.example.moviesapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.ui.viewmodel.MovieViewModel

class MovieViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}