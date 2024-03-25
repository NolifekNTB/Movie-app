package com.example.movieapp.features.Home.ui.TopHitsAnime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.shared.TopBar

@Composable
fun TopHitsAnime(mainViewModel: MainViewModel, onClick: () -> Unit) {
    val animeList by mainViewModel.getListTopHits().collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar("Top Hits Anime", onClick)
        if(animeList.isNotEmpty()){
            TopHitsAnimeList(animeList)
        }
    }
}

@Composable
fun TopHitsAnimeList(animeList: List<AnimeItemTopHits>) {
    LazyColumn(){
        items(animeList.size){ item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TopHitsAnimeListImage(animeList[item], Modifier.weight(0.5f))
                TopHitsAnimeListDetails(animeList[item], Modifier.weight(1f))
            }
        }
    }
}




















