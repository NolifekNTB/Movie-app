package com.example.movieapp.features.Home.ui

import android.annotation.SuppressLint
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
import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.features.Home.ui.composables.HomeImage
import com.example.movieapp.features.Home.ui.composables.LeftBottom
import com.example.movieapp.features.Home.ui.composables.RightTop
import com.example.movieapp.features.Home.ui.composables.rowListItems
import com.example.movieapp.features.Home.ui.composables.rowListItemsNewSeasons
import com.example.movieapp.features.Home.ui.composables.rowListTitle
import com.example.movieapp.shared.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(mainViewModel: MainViewModel, sharedViewModel: SharedViewModel, onClick: (String) -> Unit) {
    val animeList by mainViewModel.getListTopHits().collectAsState(emptyList())
    val animeListNewSeasons by mainViewModel.getListNewSeasons().collectAsState(emptyList())

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        ImageHome(sharedViewModel){ direction ->
            onClick(direction)
        }
        RowList("Top Hits Anime", animeList) { direction ->
            onClick(direction)
        }
        RowListNewSeasons("New Seasons Releases", animeListNewSeasons){ direction ->
            onClick(direction)
        }
    }

}

@Composable
fun ImageHome(sharedViewModel: SharedViewModel, onClick: (String) -> Unit) {
    Box {
        HomeImage()
        RightTop(){ direction ->
            onClick(direction)
        }
        LeftBottom(sharedViewModel, onClick)
    }
}



@Composable
fun RowList(name: String, animeList: List<AnimeItemTopHits>, onClick: (String) -> Unit){
    rowListTitle(name){ direction ->
        onClick(direction)
    }
    rowListItems(animeList){ direction ->
        onClick(direction)
    }
}

@Composable
fun RowListNewSeasons(name: String, animeList: List<AnimeItemNewSeasons>, onClick: (String) -> Unit){
    rowListTitle(name){ direction ->
        onClick(direction)
    }
    rowListItemsNewSeasons(animeList){ direction ->
        onClick(direction)
    }
}



























