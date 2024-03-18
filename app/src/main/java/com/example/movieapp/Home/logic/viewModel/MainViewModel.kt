package com.example.movieapp.Home.logic.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.retrofit.AnimeData
import com.example.movieapp.Home.data.retrofit.RetrofitInstance
import com.example.movieapp.Home.data.room.AnimeItemNewSeasons
import com.example.movieapp.Home.data.room.AnimeItemTopHits
import com.example.movieapp.Home.data.room.AnimeRepository
import com.example.movieapp.Home.data.room.AnimeRepositoryNewSeasons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AnimeRepository,
    private val repoNewSeasons: AnimeRepositoryNewSeasons
): ViewModel() {
    private val api = RetrofitInstance.createApi()
    var topHitsAnimeData = MutableStateFlow<AnimeData?>(null)
    var newSeasonsData = MutableStateFlow<AnimeData?>(null)

    init {
        /* val exampleAnimeList = mutableListOf<AnimeItem>()
         val photo = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"
         exampleAnimeList.add(AnimeItem(name = "Naruto", image = photo, rating = 9.5))
         exampleAnimeList.add(AnimeItem(name = "One Piece", image = photo, rating = 9.5))
         exampleAnimeList.add(AnimeItem(name = "Dragon Ball", image = photo, rating = 9.5))
         deleteAllAnime()
         insertALlAnime(exampleAnimeList)
         */
        deleteAllAnime()
        fetchPost()
    }

    fun fetchPost() {
        viewModelScope.launch {
            val response = api.getPost()
            topHitsAnimeData.value = response

            val responseSecond = api.getPostNewSeasons()
            newSeasonsData.value = responseSecond

         sendDataToRoom()
        }
    }

    fun sendDataToRoom(){
        if(topHitsAnimeData.value == null || newSeasonsData.value == null) return

        val animeItemList = mapAnimeDataToAnimeItem(topHitsAnimeData.value!!)
        insertALlAnime(animeItemList)

        val animeItemListNewSeasons = mapAnimeDataToNewSeasons(newSeasonsData.value!!)
        insertALlAnimeNewSeasons(animeItemListNewSeasons)
    }

    fun mapAnimeDataToAnimeItem(animeData: AnimeData): List<AnimeItemTopHits> {
        val animeItemList = mutableListOf<AnimeItemTopHits>()
        animeData.data.forEach { data ->
            // Assuming 'data' contains the necessary fields for AnimeItem
            val animeItem = AnimeItemTopHits(
                name = data.title,
                image = data.images.jpg.image_url,
                rating = data.score
            )
            animeItemList.add(animeItem)
        }
        Log.d("testowanie", "mapAnimeDataToAnimeItem -> $animeItemList")
        return animeItemList
    }

    fun mapAnimeDataToNewSeasons(animeData: AnimeData): List<AnimeItemNewSeasons> {
        val animeItemList = mutableListOf<AnimeItemNewSeasons>()
        animeData.data.forEach { data ->
            // Assuming 'data' contains the necessary fields for AnimeItem
            val animeItem = AnimeItemNewSeasons(
                name = data.title,
                image = data.images.jpg.image_url,
                rating = data.score
            )
            animeItemList.add(animeItem)
        }
        Log.d("testowanie", "mapAnimeDataToAnimeItem -> $animeItemList")
        return animeItemList
    }


    fun getAnimeList(): Flow<List<AnimeItemTopHits>> {
        return repo.getAllAnime()
    }

    fun deleteAllAnime() {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repo.deleteALlAnime()
        }
    }

    fun insertALlAnime(animeList: List<AnimeItemTopHits>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repo.insertAllAnime(
                animeList
            )
        }
    }

    fun insertALlAnimeNewSeasons(animeList: List<AnimeItemNewSeasons>) {
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoNewSeasons.insertAllAnime(
                animeList
            )
        }
    }

    fun getAnimeListNewSeasons(): Flow<List<AnimeItemNewSeasons>> {
        return repoNewSeasons.getAllAnime()
    }

}











