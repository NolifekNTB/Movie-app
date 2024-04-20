package com.example.movieapp.core.network.models.topSearches

data class Episode(
    val mal_id: Int,
    val premium: Boolean,
    val title: String,
    val url: String
)