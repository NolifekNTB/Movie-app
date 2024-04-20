package com.example.movieapp.core.network.models.shared

data class Data(
    val title: String,
    val images: Images,
    val score: Double,
    val year: Int,
    val genres: List<Genre>,
    val synopsis: String,
    val trailer: Trailer
)