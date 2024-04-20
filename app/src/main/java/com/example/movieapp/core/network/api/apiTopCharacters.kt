package com.example.movieapp.core.network.api

import com.example.movieapp.core.network.models.topCharacters.DataTopCharacters
import retrofit2.http.GET

interface AnimeApiTopCharacters {
    @GET("top/characters")
    suspend fun getTopCharacters(): DataTopCharacters
}