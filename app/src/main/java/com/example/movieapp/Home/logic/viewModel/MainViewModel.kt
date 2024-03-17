package com.example.movieapp.Home.logic.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.retrofit.AnimeData
import com.example.movieapp.Home.data.retrofit.RetrofitInstance
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.Home.data.room.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AnimeRepository
): ViewModel() {
    var postData = MutableStateFlow<AnimeData?>(null)
    private val api = RetrofitInstance.createApi()

    init {
        /* val exampleAnimeList = mutableListOf<AnimeItem>()
         val photo = "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png/revision/latest/scale-to-width-down/1200?cb=20210223094656"
         exampleAnimeList.add(AnimeItem(name = "Naruto", image = photo, rating = 9.5))
         exampleAnimeList.add(AnimeItem(name = "One Piece", image = photo, rating = 9.5))
         exampleAnimeList.add(AnimeItem(name = "Dragon Ball", image = photo, rating = 9.5))
         deleteAllAnime()
         insertALlAnime(exampleAnimeList)
         */
        fetchPost()
    }

    fun fetchPost() {
        viewModelScope.launch {
            val response = api.getPost()
            postData.value = response

         sendDataToRoom()
        }
    }

    fun sendDataToRoom(){
        if(postData.value == null) return

        val animeItemList = mapAnimeDataToAnimeItem(postData.value!!)
        insertALlAnime(animeItemList)
    }

    fun mapAnimeDataToAnimeItem(animeData: AnimeData): List<AnimeItem> {
        val animeItemList = mutableListOf<AnimeItem>()
        animeData.data.forEach { data ->
            // Assuming 'data' contains the necessary fields for AnimeItem
            val animeItem = AnimeItem(
                name = data.title,
                image = data.images.jpg.image_url,
                rating = data.score
            )
            animeItemList.add(animeItem)
        }
        Log.d("testowanie", "mapAnimeDataToAnimeItem -> $animeItemList")
        return animeItemList
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
}











