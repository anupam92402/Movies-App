package com.example.moviesapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.data.model.movie.MovieResult
import com.example.moviesapp.databinding.ActivityDetailScreenBinding

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movie") as MovieResult
        Toast.makeText(this, movie.title, Toast.LENGTH_LONG).show()
    }
}