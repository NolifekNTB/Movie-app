package com.example.movieapp.core.network

import com.example.movieapp.core.network.api.AnimeApiTopCharacters
import com.example.movieapp.core.network.api.AnimeApiTopHits
import com.example.movieapp.core.network.api.AnimeApiTopSearches
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://api.jikan.moe/v4/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val animeApiTopCharacters: AnimeApiTopCharacters by lazy {
        retrofit.create(AnimeApiTopCharacters::class.java)
    }

    val animeApiTopHits: AnimeApiTopHits by lazy {
        retrofit.create(AnimeApiTopHits::class.java)
    }

    val animeApiTopSearches: AnimeApiTopSearches by lazy {
        retrofit.create(AnimeApiTopSearches::class.java)
    }
}