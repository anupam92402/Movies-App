package com.example.moviesapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.databinding.FragmentPopularMovieBinding
import com.example.moviesapp.factory.MovieViewModelFactory
import com.example.moviesapp.ui.viewmodel.MovieViewModel
import com.example.moviesapp.utils.Constants


class PopularMovieFragment : Fragment() {

    private var _binding: FragmentPopularMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPopularMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movieApiService = Constants.getInstance()
            .create(MovieService::class.java)

        val repository = MovieRepository(movieApiService)

        viewModel =
            ViewModelProvider(this, MovieViewModelFactory(repository))
                .get(MovieViewModel::class.java)

        viewModel.popularMovie.observe(viewLifecycleOwner, {
            Log.d("popular", it.results.toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}