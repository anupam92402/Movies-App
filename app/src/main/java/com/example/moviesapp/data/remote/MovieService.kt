package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.movie.MovieResponse
import com.example.moviesapp.data.model.review.Reviews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}/reviews?")
    suspend fun getReviews(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<Reviews>

    @GET("movie/{movie_id}/similar?")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieResponse>

    @GET("search/movie?")
    suspend fun getSearchMovies(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Response<MovieResponse>
}
