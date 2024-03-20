package com.example.movieapp.Home.ui.HomeScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.core.other.TopBar

@Composable
fun NewSeasonsReleases(onClick: () -> Unit) {
    val image = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"
    val exampleList = arrayListOf(
        AnimeItemTopHits(1, "attack", image, 9.2),
        AnimeItemTopHits(2, "Naruto", image, 9.3),
        AnimeItemTopHits(3, "Dragon Ball", image, 9.5),
        AnimeItemTopHits(4, "Death Note", image, 9.1),
        AnimeItemTopHits(5, "One Piece", image, 9.4),
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar("New Seasons Releases", onClick)
        ListEpisodeReleases(exampleList)
    }

}
