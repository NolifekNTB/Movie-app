package com.example.movieapp.features.Home.data.model

import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import kotlinx.coroutines.flow.Flow

data class topHitsAndTopCharacters(
    val topHits: Flow<List<AnimeItemTopHits>>,
    val topCharacters: Flow<List<AnimeItemTopCharacters>>
)
