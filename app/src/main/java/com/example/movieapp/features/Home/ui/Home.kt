package com.example.movieapp.features.Home.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.data.model.topHitsAndTopCharacters
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.features.Home.ui.HomeComposables.BannerImage
import com.example.movieapp.features.Home.ui.HomeComposables.PlayAndMyList
import com.example.movieapp.features.Home.ui.HomeComposables.SearchAndNotifications
import com.example.movieapp.features.Home.ui.HomeComposables.RowItemsTopHits
import com.example.movieapp.features.Home.ui.HomeComposables.RowTitle
import com.example.movieapp.shared.SharedViewModel
import com.example.movieapp.features.Home.ui.HomeComposables.RowItemsTopCharacters

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    sharedViewModel: SharedViewModel,
    onNavigate: (destination: String, animeId: Int) -> Unit
) {
    val bothList = mainViewModel.getLists()

    HomeScreenLayout(
        sharedViewModel = sharedViewModel,
        bothLists = bothList,
        onNavigate = onNavigate
    )
}

@Composable
private fun HomeScreenLayout(
    sharedViewModel: SharedViewModel,
    bothLists: topHitsAndTopCharacters,
    onNavigate: (destination: String, animeId: Int) -> Unit
) {
    val topHits by bothLists.topHits.collectAsState(emptyList())
    val topCharacters by bothLists.topCharacters.collectAsState(emptyList())

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        FeaturedAnimeBanner(sharedViewModel) { direction ->
            onNavigate(direction, 0)
        }

        AnimeListRowTopHits(
            categoryTitle = stringResource(id = R.string.topHitsAnime),
            animeList = topHits)
        { direction, id ->
            onNavigate(direction, id)
        }

        AnimeListRowTopCharacters(
            categoryTitle = "Top Characters",
            animeList = topCharacters)
        { direction, id ->
            onNavigate(direction, id)
        }
    }
}

@Composable
fun FeaturedAnimeBanner(sharedViewModel: SharedViewModel, onSelect: (String) -> Unit) {
    Box {
        BannerImage()
        SearchAndNotifications(){ direction -> onSelect(direction)}
        PlayAndMyList(sharedViewModel, onSelect)
    }
}

@Composable
fun AnimeListRowTopHits(
    categoryTitle: String,
    animeList: List<AnimeItemTopHits>,
    onSelect: (String, Int) -> Unit)
{
    RowTitle(categoryTitle){ direction ->
        onSelect(direction, 0)
    }
    RowItemsTopHits(animeList){ direction, id ->
        onSelect(direction, id)
    }
}

@Composable
fun AnimeListRowTopCharacters(
    categoryTitle: String,
    animeList: List<AnimeItemTopCharacters>,
    onSelect: (String, Int) -> Unit)
{
    RowTitle(categoryTitle){ direction ->
        onSelect(direction, 0)
    }
    RowItemsTopCharacters(animeList)
}