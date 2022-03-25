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
import com.example.moviesapp.data.model.review.Result

class rv_review_adapter(val context: Context?) :
    RecyclerView.Adapter<rv_review_adapter.RVViewHolder>() {

    private var movieReviews = listOf<Result>()

    fun setReviewList(review: List<Result>) {
        this.movieReviews = review
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_review, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentMovie = movieReviews[position]

        holder.name.text = currentMovie.author
        holder.overview.text = currentMovie.content
        holder.date.text = currentMovie.created_at.subSequence(0, 10)

        if (context != null) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + currentMovie.author_details.avatar_path)
                .error(Glide.with(holder.poster).load(R.drawable.user))
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int {
        return movieReviews.size
    }

    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val overview: TextView = itemView.findViewById(R.id.tv_overview)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    }

}