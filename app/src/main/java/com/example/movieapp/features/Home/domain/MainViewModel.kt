package com.example.movieapp.features.Home.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.network.RetrofitInstance
import com.example.movieapp.core.network.models.shared.AnimeData
import com.example.movieapp.core.network.models.topCharacters.DataTopCharacters
import com.example.movieapp.features.Home.data.repositories.AnimeRepository
import com.example.movieapp.features.Home.data.repositories.AnimeRepositoryTopCharacters
import com.example.movieapp.features.Home.data.model.topHitsAndTopCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoTopHits: AnimeRepository,
    private val repoTopCharacters: AnimeRepositoryTopCharacters
): ViewModel() {
    private val apiTopHits = RetrofitInstance.animeApiTopHits
    private val apiTopCharacters = RetrofitInstance.animeApiTopCharacters

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

                val topCharactersFromDb = repoTopCharacters.getAllAnime().first()
                if(topCharactersFromDb.isEmpty()){
                    val responseTopCharacters = apiTopCharacters.getTopCharacters()
                    insertTopCharacters(mapRetrofitToRoomTopCharacters(responseTopCharacters))
                    Log.d("", "topCharacters -> $responseTopCharacters")
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
    private fun mapRetrofitToRoomTopCharacters(animeData: DataTopCharacters): List<AnimeItemTopCharacters> {
        val animeItemList = mutableListOf<AnimeItemTopCharacters>()
        animeData.data.forEach { data ->
            val animeItem = AnimeItemTopCharacters(
                name = data.name,
                image = data.images.jpg.image_url,

            )
            animeItemList.add(animeItem)
        }
        return animeItemList
    }


    fun getLists(): topHitsAndTopCharacters {
        val list1 = getListTopHits()
        val list2 = getListNewSeasons()

        return topHitsAndTopCharacters(
            topHits = list1,
            topCharacters = list2
        )
    }

    fun getListTopHits(): Flow<List<AnimeItemTopHits>> {
        return repoTopHits.getAllAnime()
    }
    fun getListNewSeasons(): Flow<List<AnimeItemTopCharacters>> {
        return repoTopCharacters.getAllAnime()
    }

    private fun insertTopHits(animeList: List<AnimeItemTopHits>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoTopHits.insertAllAnime(
                animeList
            )
        }
    }
    private fun insertTopCharacters(animeList: List<AnimeItemTopCharacters>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoTopCharacters.insertAllAnime(
                animeList
            )
        }
    }

    suspend fun searchAllAnime(query: String): List<AnimeItemTopHits> {
        return repoTopHits.searchAnimeByName(query)
    }
}