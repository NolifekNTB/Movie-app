package com.example.movieapp.core.network.api

import com.example.movieapp.core.network.models.shared.AnimeData
import com.example.movieapp.core.network.models.topSearches.topSearches
import retrofit2.http.GET

interface AnimeApiTopSearches {
    @GET("watch/episodes/popular")
    suspend fun getTopSearches(): topSearches
}