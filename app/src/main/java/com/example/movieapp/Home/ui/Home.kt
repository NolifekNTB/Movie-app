package com.example.movieapp.Home.ui

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
import com.example.movieapp.Home.data.room.newSeasons.AnimeItemNewSeasons
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.Home.logic.viewModel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MainViewModel, onClick: (String) -> Unit) {
    val animeList by viewModel.getListTopHits().collectAsState(emptyList())
    val animeListNewSeasons by viewModel.getListNewSeasons().collectAsState(emptyList())

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        ImageHome(viewModel){ direction ->
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
fun ImageHome(viewModel: MainViewModel, onClick: (String) -> Unit) {
    Box {
        HomeImage()
        RightTop(){ direction ->
            onClick(direction)
        }
        LeftBottom(viewModel)
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



























