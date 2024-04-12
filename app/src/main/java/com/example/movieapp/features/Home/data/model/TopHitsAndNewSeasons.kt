package com.example.movieapp.features.Home.data.model

import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import kotlinx.coroutines.flow.Flow

data class topHitsAndNewSeasons(
    val topHits: Flow<List<AnimeItemTopHits>>,
    val newSeasons: Flow<List<AnimeItemNewSeasons>>
)
