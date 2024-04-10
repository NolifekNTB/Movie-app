package com.example.movieapp.features.Home.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.network.RetrofitInstance
import com.example.movieapp.core.network.models.AnimeData
import com.example.movieapp.features.Home.data.AnimeRepository
import com.example.movieapp.features.Home.data.AnimeRepositoryNewSeasons
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
    private val apiTopHits = RetrofitInstance.animeApiTopHits
    private val apiNewSeasons = RetrofitInstance.animeApiSeasonsUpcoming

    init {
        fetchPost()
    }

    fun fetchPost() {
        viewModelScope.launch {
            try{
                val topHitsFromDb = repoTopHits.getAllAnime().first()
                if (topHitsFromDb.isEmpty()) {
                    val responseTopHits = apiTopHits.getTopHits()
                    insertTopHits(mapRetrofitToRoomTopHits(responseTopHits))
                }

                val newSeasonsFromDb = repoNewSeasons.getAllAnime().first()
                if(newSeasonsFromDb.isEmpty()){
                    val responseNewSeasons = apiNewSeasons.getNewSeasons()
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
                rating = data.score,
                year = data.year,
                genres = data.genres,
                description = data.synopsis,
                trailer = data.trailer
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

    data class topHitsAndNewSeasons(
        val topHits: Flow<List<AnimeItemTopHits>>,
        val newSeasons: Flow<List<AnimeItemNewSeasons>>
    )

    fun getLists(): topHitsAndNewSeasons {
        val list1 = getListTopHits()
        val list2 = getListNewSeasons()

        return topHitsAndNewSeasons(
            topHits = list1,
            newSeasons = list2

        )
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

    suspend fun searchAllAnime(query: String): List<AnimeItemTopHits> {
        return repoTopHits.searchAnimeByName(query)
    }

}











