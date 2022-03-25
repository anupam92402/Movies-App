package com.example.moviesapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.data.model.movie.MovieResult
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.databinding.FragmentUpcomingMovieBinding
import com.example.moviesapp.factory.MovieViewModelFactory
import com.example.moviesapp.ui.activity.DetailScreenActivity
import com.example.moviesapp.ui.adapter.rv_upcoming_adapter
import com.example.moviesapp.ui.viewmodel.MovieViewModel
import com.example.moviesapp.utils.Constants


class UpcomingMovieFragment : Fragment(), rv_upcoming_adapter.onMovieClickListener {

    private var _binding: FragmentUpcomingMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: rv_upcoming_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpcomingMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = rv_upcoming_adapter(context, this)
        binding.rvUpcomingMovies.adapter = adapter
        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(view.context)
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

        viewModel.upcomingMovie.observe(viewLifecycleOwner, {
            adapter.setMovieList(it.results)
            Log.d("upcoming", it.results.toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClick(movieResult: MovieResult) {
        val intent = Intent(context, DetailScreenActivity::class.java)
        intent.putExtra("movie", movieResult)
        startActivity(intent)
    }

}