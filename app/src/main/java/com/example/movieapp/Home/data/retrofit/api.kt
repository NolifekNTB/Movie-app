package com.example.movieapp.Home.data.retrofit

import retrofit2.http.GET

interface AnimeApi {
    @GET("top/anime")
    suspend fun getPost(): AnimeData

    @GET("seasons/upcoming")
    suspend fun getPostNewSeasons(): AnimeData
}