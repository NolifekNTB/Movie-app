package com.example.movieapp.Home.data.retrofit

import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("v4/top/anime")
    suspend fun getPost(): AnimeData
}