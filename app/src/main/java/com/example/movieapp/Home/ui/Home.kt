package com.example.movieapp.Home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.Home.data.room.AnimeItem

@Composable
fun HomeScreen(onPlayClicked: (String) -> Unit, animeList: List<AnimeItem> = emptyList()) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        ImageHome(onPlayClicked)
        RowList("Top Hits Anime", animeList, onPlayClicked)
        RowList("New Episode Releases", animeList, onPlayClicked)
    }
}

@Composable
fun ImageHome(onPlayClicked: (String) -> Unit) {
    Box {
        HomeImage()
        RightTop(onPlayClicked)
        LeftBottom()
    }
}



@Composable
fun RowList(name: String, animeList: List<AnimeItem>, onPlayClicked: (String) -> Unit){
    rowListTitle(name, onPlayClicked)
    rowListItems(animeList)
}
























