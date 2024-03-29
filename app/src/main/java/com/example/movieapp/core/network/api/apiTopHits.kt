package com.example.movieapp.core.network.api

import com.example.movieapp.core.network.models.AnimeData
import retrofit2.http.GET

interface AnimeApiTopHits {
    @GET("top/anime")
    suspend fun getTopHits(): AnimeData
}