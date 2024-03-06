package com.example.movieapp.MyList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.Home.ui.HomeScreens.ListEpisodeReleases
import com.example.movieapp.Home.ui.HomeScreens.NewEpisodeReleases
import com.example.movieapp.Home.ui.HomeScreens.Search.NotFound
import com.example.movieapp.MyList.logic.ListViewModel
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar

@Composable
fun MyList(onClick: () -> Unit) {
    val viewModel = ListViewModel()
    val animeList = viewModel.exampleList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(name = "My List") { onClick() }
//        NotFound(title = "Your list is empty", text = "It seems that you haven't added" +
//                "any anime to the list")
        ListEpisodeReleases(animeList = animeList)
    }

}