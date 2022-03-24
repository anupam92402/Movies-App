package com.example.moviesapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.movie.MovieResult

class rv_top_rated_adapter : RecyclerView.Adapter<rv_top_rated_adapter.RVViewHolder>() {

    private var topRatedMovies = listOf<MovieResult>()

    fun setMovieList(movies: List<MovieResult>) {
        this.topRatedMovies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_popular_movie, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentMovie = topRatedMovies[position]
        holder.title.text = currentMovie.title
        holder.overview.text = currentMovie.overview
        holder.popularity.text = currentMovie.popularity.toString()
    }

    override fun getItemCount(): Int {
        return topRatedMovies.size
    }

    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val overview: TextView = itemView.findViewById(R.id.tv_overview)
        val popularity: TextView = itemView.findViewById(R.id.tv_popularity)
    }

}