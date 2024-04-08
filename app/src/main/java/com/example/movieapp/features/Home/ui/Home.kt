package com.example.movieapp.features.Home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.features.Home.ui.HomeComposables.BannerImage
import com.example.movieapp.features.Home.ui.HomeComposables.PlayAndMyList
import com.example.movieapp.features.Home.ui.HomeComposables.SearchAndNotifications
import com.example.movieapp.features.Home.ui.HomeComposables.CategoryItemsTopHits
import com.example.movieapp.features.Home.ui.HomeComposables.CategoryItemsNewSeasons
import com.example.movieapp.features.Home.ui.HomeComposables.CategoryTitle
import com.example.movieapp.shared.SharedViewModel

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    sharedViewModel: SharedViewModel,
    onNavigate: (destination: String, animeId: Int) -> Unit
) {
    val topHitsAnime = mainViewModel.getListTopHits().collectAsState(emptyList())
    val newSeasonsAnime = mainViewModel.getListNewSeasons().collectAsState(emptyList())

    HomeScreenLayout(
        sharedViewModel = sharedViewModel,
        topHitsAnimeList = topHitsAnime.value,
        newSeasonsAnimeList = newSeasonsAnime.value,
        onNavigate = onNavigate
    )
}

@Composable
private fun HomeScreenLayout(
    sharedViewModel: SharedViewModel,
    topHitsAnimeList: List<AnimeItemTopHits>,
    newSeasonsAnimeList: List<AnimeItemNewSeasons>,
    onNavigate: (destination: String, animeId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        FeaturedAnimeBanner(sharedViewModel) { direction ->
            onNavigate(direction, 0)
        }

        AnimeCategoryListTopHits(
            categoryTitle = stringResource(id = R.string.topHitsAnime),
            animeList = topHitsAnimeList)
        { direction, id ->
            onNavigate(direction, id)
        }

        AnimeCategoryListNewSeasons(
            categoryTitle = stringResource(id = R.string.newSeasonsReleases),
            animeList = newSeasonsAnimeList)
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
fun AnimeCategoryListTopHits(
    categoryTitle: String,
    animeList: List<AnimeItemTopHits>,
    onSelect: (String, Int) -> Unit)
{
    CategoryTitle(categoryTitle){ direction ->
        onSelect(direction, 0)
    }
    CategoryItemsTopHits(animeList){ direction, id ->
        onSelect(direction, id)
    }
}

@Composable
fun AnimeCategoryListNewSeasons(
    categoryTitle: String,
    animeList: List<AnimeItemNewSeasons>,
    onSelect: (String, Int) -> Unit
){
    CategoryTitle(categoryTitle){ direction ->
        onSelect(direction, 0)
    }
    CategoryItemsNewSeasons(animeList){ direction, id ->
        onSelect(direction, id)
    }
}