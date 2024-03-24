package com.example.movieapp.features.Home.ui.TopHitsAnime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.shared.TopBar

@Composable
fun TopHitsAnime(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar("Top Hits Anime", onClick)
        TopHitsAnimeList()
    }
}

@Composable
fun TopHitsAnimeList(){
    LazyColumn(){
        items(4){ item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TopHitsAnimeListImage(item)
                TopHitsAnimeListDetails()
            }
        }
    }
}




















