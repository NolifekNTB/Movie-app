package com.example.movieapp.Home.logic.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.retrofit.AnimeData
import com.example.movieapp.Home.data.retrofit.RetrofitInstance
import com.example.movieapp.Home.data.room.newSeasons.AnimeItemNewSeasons
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import com.example.movieapp.Home.data.room.topHits.AnimeRepository
import com.example.movieapp.Home.data.room.newSeasons.AnimeRepositoryNewSeasons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoTopHits: AnimeRepository,
    private val repoNewSeasons: AnimeRepositoryNewSeasons
): ViewModel() {
    private val api = RetrofitInstance.createApi()

    init {
        fetchPost()
    }

    fun fetchPost() {
        viewModelScope.launch {
            try{
                val topHitsFromDb = repoTopHits.getAllAnime().first()
                val newSeasonsFromDb = repoNewSeasons.getAllAnime().first()

                Log.d("testowanie", "fetchPost -> $topHitsFromDb")
                if (topHitsFromDb.isEmpty()) {
                    val responseTopHits = api.getTopHits()
                    insertTopHits(mapRetrofitToRoomTopHits(responseTopHits))
                }
                if(newSeasonsFromDb.isEmpty()){
                    val responseNewSeasons = api.getNewSeasons()
                    insertNewSeasons(mapRetrofitToRoomNewSeasons(responseNewSeasons))
                }
            } catch (e: Exception){
                Log.e("codeDebugging", "fetchPost -> $e")
            }
        }
    }

    private fun mapRetrofitToRoomTopHits(animeData: AnimeData): List<AnimeItemTopHits> {
        val animeItemList = mutableListOf<AnimeItemTopHits>()
        animeData.data.forEach { data ->
            val animeItem = AnimeItemTopHits(
                name = data.title,
                image = data.images.jpg.image_url,
                rating = data.score
            )
            animeItemList.add(animeItem)
        }
        return animeItemList
    }

    private fun mapRetrofitToRoomNewSeasons(animeData: AnimeData): List<AnimeItemNewSeasons> {
        val animeItemList = mutableListOf<AnimeItemNewSeasons>()
        animeData.data.forEach { data ->
            val animeItem = AnimeItemNewSeasons(
                name = data.title,
                image = data.images.jpg.image_url,
                rating = data.score
            )
            animeItemList.add(animeItem)
        }
        return animeItemList
    }


    fun getListTopHits(): Flow<List<AnimeItemTopHits>> {
        return repoTopHits.getAllAnime()
    }

    private fun deleteTopHits() {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoTopHits.deleteALlAnime()
        }
    }

    private fun insertTopHits(animeList: List<AnimeItemTopHits>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoTopHits.insertAllAnime(
                animeList
            )
        }
    }

    fun getListNewSeasons(): Flow<List<AnimeItemNewSeasons>> {
        return repoNewSeasons.getAllAnime()
    }

    private fun deleteNewSeasons(){
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoNewSeasons.deleteALlAnime()
        }
    }

    private fun insertNewSeasons(animeList: List<AnimeItemNewSeasons>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoNewSeasons.insertAllAnime(
                animeList
            )
        }
    }

}











