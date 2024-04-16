package com.example.movieapp.core.network.models.topCharacters

data class Data(
    val about: String,
    val favorites: Int,
    val images: Images,
    val mal_id: Int,
    val name: String,
    val name_kanji: String,
    val nicknames: List<String>,
    val url: String
)