package com.example.moviesapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.repository.DetailScreenRepository
import com.example.moviesapp.ui.viewmodel.DetailScreenViewModel

class DetailScreenViewModelFactory(private val repository: DetailScreenRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailScreenViewModel(repository) as T
    }
}