package com.example.movieapp.core.network.api

import com.example.movieapp.core.network.models.shared.AnimeData
import retrofit2.http.GET

interface AnimeApiSeasonsUpcoming {
    @GET("seasons/upcoming")
    suspend fun getNewSeasons(): AnimeData
}