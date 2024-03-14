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

    val list = arrayListOf(
        AnimeItem(id = 0, "Attack on titan test", R.drawable.home_attackontitan, 9.9),
        AnimeItem(id = 1, "Naruto", R.drawable.home_naruto, 9.2),
        AnimeItem(id = 2, "Dragon ball", R.drawable.home_demonslayer, 8.2),
        AnimeItem(id = 3, "Cos innego", R.drawable.home_attackontitan, 8.4),
    )
    init {
        deleteAllAnime()
        insertALlAnime(list)
    }



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

    private val _applyFilters: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val applyFilters: StateFlow<Boolean> = _applyFilters.asStateFlow()

    fun remove(element: String){
        _filtersList.value -= element
    }

    fun add(element: String){
        _filtersList.value += element
    }

    fun resetFilters(){
        _filtersList.value = listOf()
    }

    fun applyFilters(value: Boolean){
        _applyFilters.value = value
    }
}










