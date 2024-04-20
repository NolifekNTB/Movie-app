package com.example.movieapp.core.network.models.topSearches

data class Entry(
    val images: Images,
    val mal_id: Int,
    val title: String,
    val url: String
)