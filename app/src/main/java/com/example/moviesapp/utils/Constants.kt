package com.example.moviesapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "db75be3f6da59e6c54d0b9f568d19d16"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}