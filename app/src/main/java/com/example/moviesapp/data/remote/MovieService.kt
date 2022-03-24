package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie")
    suspend fun getPopularMovies(
        @Query("popular") popular: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}