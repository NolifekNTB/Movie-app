package com.example.movieapp

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.AnimeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _animeList = MutableStateFlow<List<AnimeItem>>(emptyList())
    val animeList: StateFlow<List<AnimeItem>> = _animeList.asStateFlow()

    init {
        _animeList.value = arrayListOf(
            AnimeItem(id = 0, "Attack on titan test", R.drawable.attackontitan, 9.9),
            AnimeItem(id = 1, "Naruto", R.drawable.demon_slayer, 9.2),
            AnimeItem(id = 2, "Dragon ball", R.drawable.error, 8.2),
            AnimeItem(id = 3, "Cos innego", R.drawable.attackontitan, 8.4),
        )
    }
}