package com.example.movieapp.core.MyList.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.core.MyList.data.AnimeItemMyList
import com.example.movieapp.core.MyList.data.AnimeRepositoryMyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repoMyList: AnimeRepositoryMyList
): ViewModel() {

    init{

    }

    fun getListMyList(): Flow<List<AnimeItemMyList>> {
        return repoMyList.getAllAnime()
    }

    fun insertAnimeItem(item: AnimeItemMyList){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repoMyList.insertAnime(item)
        }
    }

    private fun insertListMyList(animeMyList: AnimeItemMyList){
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoMyList.insertAnime(animeMyList)
        }
    }

    private fun deleteMyList(){
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoMyList.deleteALlAnime()
        }
    }

    fun deleteAnimeItem(animeItem: AnimeItemMyList){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repoMyList.deleteAnime(animeItem)
        }
    }

    suspend fun searchAllAnime(query: String): List<AnimeItemMyList> {
        return repoMyList.searchAnimeByName(query)
    }
}