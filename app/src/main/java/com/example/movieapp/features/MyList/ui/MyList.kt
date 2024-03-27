package com.example.movieapp.features.MyList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.ui.Search.NotFound
import com.example.movieapp.features.Home.ui.composables.ListEpisodeReleases
import com.example.movieapp.shared.SharedViewModel
import com.example.movieapp.shared.TopBar

@Composable
fun MyList(viewModel: SharedViewModel, onClick: () -> Unit) {
    val animeList by viewModel.getListMyList().collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(name = "My List") { onClick() }
        if(animeList.isEmpty()){
            NotFound(title = "Your list is empty", text = "It seems that you haven't added" +
                    "any anime to the list")
        } else {
            ListEpisodeReleases(animeList = fromMyListToTopHits(animeList))
        }
    }
}

fun fromMyListToTopHits(animeList: List<AnimeItemMyList>): List<AnimeItemTopHits> {
    val list = mutableListOf<AnimeItemTopHits>()
    for (i in animeList) {
        val anime = AnimeItemTopHits(
            id = i.id,
            name = i.name,
            image = i.image,
            rating = i.rating,
            year = 2024,
            genres = emptyList()
        )
        list.add(anime)
    }
    return list
}