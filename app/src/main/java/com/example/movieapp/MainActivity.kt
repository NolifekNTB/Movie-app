package com.example.movieapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.data.AnimeItem
import com.example.movieapp.room.AnimeDatabase
import com.example.movieapp.ui.navigation.BottomNavigationMenu
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.fillMaxSize()) {
                val viewModel = hiltViewModel<MainViewModel>()
                val animeList by viewModel.getAnimeList().collectAsState(emptyList())
                Log.d("listaAnime", animeList.toString())


                BottomNavigationMenu(animeList = animeList, viewModel = viewModel)
            }
        }
    }
}


