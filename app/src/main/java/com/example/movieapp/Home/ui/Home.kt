package com.example.movieapp.Home.ui

import android.annotation.SuppressLint
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
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.Home.logic.viewModel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: MainViewModel, onClick: (String) -> Unit) {
    val animeList by viewModel.getAnimeList().collectAsState(emptyList())
    Log.d("testowanko", animeList.toString())

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        ImageHome(){ direction ->
            onClick(direction)
        }
        RowList("Top Hits Anime", animeList) { direction ->
            onClick(direction)
        }
        RowList("New Episodes Releases", animeList){ direction ->
            onClick(direction)
        }
    }

}

@Composable
fun ImageHome(onClick: (String) -> Unit) {
    Box {
        HomeImage()
        RightTop(){ direction ->
            onClick(direction)
        }
        LeftBottom()
    }
}



@Composable
fun RowList(name: String, animeList: List<AnimeItem>, onClick: (String) -> Unit){
    rowListTitle(name){ direction ->
        onClick(direction)
    }
    rowListItems(animeList){ direction ->
        onClick(direction)
    }
}
























