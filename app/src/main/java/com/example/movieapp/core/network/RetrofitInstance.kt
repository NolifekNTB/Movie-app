package com.example.movieapp.core.network

import com.example.movieapp.core.network.api.AnimeApiSeasonsUpcoming
import com.example.movieapp.core.network.api.AnimeApiTopHits
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://api.jikan.moe/v4/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val animeApiSeasonsUpcoming: AnimeApiSeasonsUpcoming by lazy {
        retrofit.create(AnimeApiSeasonsUpcoming::class.java)
    }

    val animeApiTopHits: AnimeApiTopHits by lazy {
        retrofit.create(AnimeApiTopHits::class.java)
    }

    /*fun createApi(): AnimeApiTopHits {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AnimeApiTopHits::class.java)
    }
     */
}