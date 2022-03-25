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

class rv_similar_adapter(val context: Context?) :
    RecyclerView.Adapter<rv_similar_adapter.RVViewHolder>() {

    private var similarMovie = listOf<MovieResult>()

    fun setSimilarMovieList(movie: List<MovieResult>) {
        this.similarMovie = movie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_similar, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentMovie = similarMovie[position]

        holder.title.text = currentMovie.title
        holder.overview.text = currentMovie.overview
        if (context != null) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + currentMovie.poster_path)
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int {
        return similarMovie.size
    }

    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val overview: TextView = itemView.findViewById(R.id.tv_overview)
        val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    }

}