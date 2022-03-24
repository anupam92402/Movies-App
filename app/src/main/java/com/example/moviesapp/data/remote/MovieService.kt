package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/upcoming?")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/top_rated?")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}