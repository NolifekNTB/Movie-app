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
    val exampleList = arrayListOf(
        AnimeItem(1, "attack", R.drawable.home_attackontitan, 9.2),
        AnimeItem(2, "Naruto", R.drawable.home_naruto, 9.3),
        AnimeItem(3, "Dragon Ball", R.drawable.home_attackontitan, 9.5),
        AnimeItem(4, "Death Note", R.drawable.home_naruto, 9.1),
        AnimeItem(5, "One Piece", R.drawable.home_attackontitan, 9.4),
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
