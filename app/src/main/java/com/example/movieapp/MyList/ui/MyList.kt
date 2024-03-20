package com.example.movieapp.MyList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.Home.ui.HomeScreens.ListEpisodeReleases
import com.example.movieapp.MyList.data.AnimeItemMyList
import com.example.movieapp.MyList.logic.ListViewModel
import com.example.movieapp.core.other.TopBar

@Composable
fun MyList(onClick: () -> Unit) {
   val viewModel = hiltViewModel<ListViewModel>()
   val animeList by viewModel.getListMyList().collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(name = "My List") { onClick() }
//        NotFound(title = "Your list is empty", text = "It seems that you haven't added" +
//                "any anime to the list")
        ListEpisodeReleases(animeList = fromMyListToTopHits(animeList))
    }
}

fun fromMyListToTopHits(animeList: List<AnimeItemMyList>): List<AnimeItemTopHits> {
    val list = mutableListOf<AnimeItemTopHits>()
    for (i in animeList) {
        val anime = AnimeItemTopHits(
            id = i.id,
            name = i.name,
            image = i.image,
            rating = i.rating
        )
        list.add(anime)
    }
    return list
}