package com.example.moviesapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.data.model.movie.MovieResult
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.databinding.FragmentTopRatedMovieBinding
import com.example.moviesapp.factory.MovieViewModelFactory
import com.example.moviesapp.ui.activity.DetailScreenActivity
import com.example.moviesapp.ui.adapter.rv_top_rated_adapter
import com.example.moviesapp.ui.viewmodel.MovieViewModel
import com.example.moviesapp.utils.Constants

class TopRatedMovieFragment : Fragment(), rv_top_rated_adapter.onMovieClickListener {

    private var _binding: FragmentTopRatedMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: rv_top_rated_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopRatedMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = rv_top_rated_adapter(context, this)
        binding.rvTopRatedMovies.adapter = adapter
        binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(view.context)
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

        viewModel.topRatedMovie.observe(viewLifecycleOwner, {
            adapter.setMovieList(it.results)
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