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

class rv_top_rated_adapter(val context: Context?, private var listener: onMovieClickListener) :
    RecyclerView.Adapter<rv_top_rated_adapter.RVViewHolder>() {

    private var topRatedMovies = listOf<MovieResult>()

    fun setMovieList(movies: List<MovieResult>) {
        this.topRatedMovies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_top_rated_movie, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentMovie = topRatedMovies[position]
        holder.itemView.setOnClickListener {
            listener.onMovieClick(topRatedMovies[position])
        }
        holder.title.text = currentMovie.title
        holder.overview.text = currentMovie.overview
        holder.language.text = currentMovie.original_language
        holder.vote_average.text = currentMovie.vote_average.toString()
        holder.vote_count.text = currentMovie.vote_count.toString()
        if (context != null) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + currentMovie.poster_path)
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int {
        return topRatedMovies.size
    }

    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val overview: TextView = itemView.findViewById(R.id.tv_overview)
        val poster: ImageView = itemView.findViewById(R.id.iv_poster)
        val language: TextView = itemView.findViewById(R.id.tv_language)
        val vote_count: TextView = itemView.findViewById(R.id.tv_vote_count)
        val vote_average: TextView = itemView.findViewById(R.id.tv_vote_average)
    }

    interface onMovieClickListener {
        fun onMovieClick(movieResult: MovieResult)
    }

}