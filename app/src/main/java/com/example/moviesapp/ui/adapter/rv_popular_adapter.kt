package com.example.moviesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.movie.MovieResult

class rv_popular_adapter(val context: Context?, private var listener: onMovieClickListener) :
    RecyclerView.Adapter<rv_popular_adapter.RVViewHolder>() {

    private var popularMovies = listOf<MovieResult>()

    fun setMovieList(movies: List<MovieResult>) {
        this.popularMovies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_popular_movie, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentMovie = popularMovies[position]
        holder.itemView.setOnClickListener {
            listener.onMovieClick(popularMovies[position])
        }
        currentMovie.popularity = currentMovie.popularity / 100
        holder.title.text = currentMovie.title
        holder.overview.text = currentMovie.overview
        holder.popularity.text = currentMovie.popularity.toString()
        holder.language.text = currentMovie.original_language
        if (context != null) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + currentMovie.poster_path)
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val overview: TextView = itemView.findViewById(R.id.tv_overview)
        val popularity: TextView = itemView.findViewById(R.id.tv_popularity)
        val language: TextView = itemView.findViewById(R.id.tv_language)
        val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    }

    interface onMovieClickListener {
        fun onMovieClick(movieResult: MovieResult)
    }

}