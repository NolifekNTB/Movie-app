package com.example.movieapp.features.MyList.domain

import androidx.lifecycle.ViewModel
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.network.models.shared.Trailer

class MyListViewModel: ViewModel() {

    fun FromMyListToTopHits(animeList: List<AnimeItemMyList>): List<AnimeItemTopHits> {
        val list = mutableListOf<AnimeItemTopHits>()
        for (i in animeList) {
            val anime = AnimeItemTopHits(
                id = i.id,
                name = i.name,
                image = i.image,
                rating = i.rating,
                year = 2024,
                genres = emptyList(),
                description = "",
                trailer = Trailer("")
            )
            list.add(anime)
        }
        return list
    }
}