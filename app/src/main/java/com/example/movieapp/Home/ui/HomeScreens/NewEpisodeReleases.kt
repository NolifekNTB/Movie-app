package com.example.movieapp.Home.ui.HomeScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.R
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.core.other.TopBar

@Composable
fun NewEpisodeReleases(onClick: () -> Unit) {
    val image = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"
    val exampleList = arrayListOf(
        AnimeItem(1, "attack", image, 9.2),
        AnimeItem(2, "Naruto", image, 9.3),
        AnimeItem(3, "Dragon Ball", image, 9.5),
        AnimeItem(4, "Death Note", image, 9.1),
        AnimeItem(5, "One Piece", image, 9.4),
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar("New Episode Releases", onClick)
        ListEpisodeReleases(exampleList)
    }

}
