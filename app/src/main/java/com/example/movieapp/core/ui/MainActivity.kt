package com.example.movieapp.core.ui

import  android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.movieapp.BottomNavMenu.ui.BottomNavigationMenu
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.Home.logic.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.fillMaxSize()) {
                val viewModel = hiltViewModel<MainViewModel>()
                val animeList by viewModel.getAnimeList().collectAsState(emptyList())

                BottomNavigationMenu(animeList = animeList, viewModel = viewModel)
            }
        }
    }
}


