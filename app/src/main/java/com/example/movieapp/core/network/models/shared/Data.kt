package com.example.movieapp.core.network.models.shared

import com.example.movieapp.core.network.models.topHits.Genre

data class Data(
    val title: String,
    val images: Images,
    val score: Double,
    val year: Int,
    val genres: List<Genre>
)