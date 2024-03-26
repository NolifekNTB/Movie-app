package com.example.movieapp.features.Home.ui.NewSeasonsReleases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.ui.composables.ListEpisodeReleases
import com.example.movieapp.shared.TopBar

@Composable
fun NewSeasonsReleases(onClick: () -> Unit) {
    val image = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"
    val exampleList = arrayListOf(
        AnimeItemTopHits(1, "attack", image, 9.2, 2024, emptyList()),
        AnimeItemTopHits(2, "Naruto", image, 9.3, 2024, emptyList()),
        AnimeItemTopHits(3, "Dragon Ball", image, 9.5, 2024, emptyList()),
        AnimeItemTopHits(4, "Death Note", image, 9.1, 2024, emptyList()),
        AnimeItemTopHits(5, "One Piece", image, 9.4, 2024, emptyList()),
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
