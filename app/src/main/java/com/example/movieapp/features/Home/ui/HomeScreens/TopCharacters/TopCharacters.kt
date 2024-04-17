package com.example.movieapp.features.Home.ui.HomeScreens.TopCharacters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.features.Home.ui.HomeComposables.ElementCardTopCharacters
import com.example.movieapp.features.Home.ui.HomeScreens.EpisodeItem
import com.example.movieapp.features.Home.ui.HomeScreens.ListEpisodeReleases
import com.example.movieapp.shared.TopBar

@Composable
fun NewSeasonsReleases(
    mainViewModel: MainViewModel,
    onNavigate: () -> Unit
) {
    val topCharactersList by mainViewModel.getLists().topCharacters.collectAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar("Top Characters"){_ -> onNavigate() }
        ListEpisodeTopCharacters(topCharactersList)
    }
}

@Composable
fun ListEpisodeTopCharacters(animeList: List<AnimeItemTopCharacters>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(animeList.size){ item ->
            EpisodeItemTopCharacters(animeList[item])
        }
    }
}

@Composable
fun EpisodeItemTopCharacters(episode: AnimeItemTopCharacters) {
    Box {
        Card(){
            AsyncImage(
                model = episode.image,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                alpha = 0.75f,
                modifier = Modifier.size(250.dp, 250.dp)
            )
        }
        ElementCardTopCharacters(episode.name)
    }
}



