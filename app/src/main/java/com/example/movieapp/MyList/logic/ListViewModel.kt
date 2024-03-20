package com.example.movieapp.MyList.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.MyList.data.AnimeItemMyList
import com.example.movieapp.MyList.data.AnimeRepositoryMyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repoMyList: AnimeRepositoryMyList
): ViewModel() {

    init {
        deleteMyList()
        val list = AnimeItemMyList(
            id = 0,
            name = "Test",
            image = "https://m.media-amazon.com/images/M/MV5BNjRiNmNjMmMtN2U2Yi00ODgxLTk3OTMtMmI1MTI1NjYyZTEzXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_FMjpg_UX1000_.jpg",
            rating = 0.0
        )
        insertListMyList(list)
    }

    fun getListMyList(): Flow<List<AnimeItemMyList>> {
        return repoMyList.getAllAnime()
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
}