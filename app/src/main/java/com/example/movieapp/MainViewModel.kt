package com.example.movieapp

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.AnimeItem
import com.example.movieapp.room.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(app: Application): AndroidViewModel(app) {
    //ROOM
    private val repo = AnimeRepository(app.applicationContext)

    /* To test ROOM
    val list = arrayListOf(
        AnimeItem(id = 0, "Attack on titan test", R.drawable.attackontitan, 9.9),
        AnimeItem(id = 1, "Naruto", R.drawable.naruto, 9.2),
        AnimeItem(id = 2, "Dragon ball", R.drawable.demon_slayer, 8.2),
        AnimeItem(id = 3, "Cos innego", R.drawable.attackontitan, 8.4),
    )
    init {
        //deleteAllAnime()
        //insertALlAnime(list)
    }
     */

    fun getAnimeList(): Flow<List<AnimeItem>> {
        return repo.getAllAnime()
    }

    fun deleteAllAnime() {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repo.deleteALlAnime()
        }
    }

    fun insertALlAnime(animeList: List<AnimeItem>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repo.insertAllAnime(
                animeList
            )
        }
    }
}