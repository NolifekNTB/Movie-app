package com.example.movieapp.Home.logic.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.Home.data.room.AnimeRepository
import com.example.movieapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AnimeRepository
): ViewModel() {

    /*
    val list = arrayListOf(
        AnimeItem(id = 0, "Attack on titan test", R.drawable.attackontitan, 9.9),
        AnimeItem(id = 1, "Naruto", R.drawable.naruto, 9.2),
        AnimeItem(id = 2, "Dragon ball", R.drawable.demon_slayer, 8.2),
        AnimeItem(id = 3, "Cos innego", R.drawable.attackontitan, 8.4),
    )
    init {
        deleteAllAnime()
        insertALlAnime(list)
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

    suspend fun searchAllAnime(query: String): List<AnimeItem> {
        return repo.searchAnimeByName(query)
    }

    private val _filtersList = MutableStateFlow<List<String>>(listOf())
    val filtersList: StateFlow<List<String>> = _filtersList.asStateFlow()

    fun updateList(s: String) {
        val currentList = _filtersList.value.toMutableList()
        currentList.add(s)
        _filtersList.value = currentList
    }

    fun removeFromList(s: String) {
        val currentList = _filtersList.value.toMutableList()
        currentList.remove(s)
        _filtersList.value = currentList
    }
}










