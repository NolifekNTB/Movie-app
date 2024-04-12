package com.example.movieapp.features.Details.domain

import androidx.lifecycle.ViewModel
import com.example.movieapp.core.database.entities.AnimeItemTopHits

class DetailViewModel: ViewModel() {
    fun filterAnimeList(animeList: List<AnimeItemTopHits>, animeItem: AnimeItemTopHits): List<AnimeItemTopHits> {
        return animeList.filter { it.id != animeItem.id && it.genres.any { genre -> animeItem.genres.contains(genre) } }
    }
}