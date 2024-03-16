package com.example.movieapp.Home.logic.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Home.data.retrofit.AnimeData
import com.example.movieapp.Home.data.retrofit.RetrofitInstance
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
    var postData = MutableStateFlow<AnimeData?>(null)

    private val api = RetrofitInstance.createApi()

    fun fetchPost() {
        viewModelScope.launch {
            try{
                val post = api.getPost()
                postData.value = post
            } catch (e: Exception){
                Log.d("testowanie", "fetchPost: $e")
            }
        }
    }

    fun sendDataToRoom(){
        if(postData.value == null){
            return
        }
        val animeItemList = mapAnimeDataToAnimeItem(postData.value!!)
        Log.d("testowanie", "map $animeItemList")
        insertALlAnime(animeItemList)
    }

    fun mapAnimeDataToAnimeItem(animeData: AnimeData): List<AnimeItem> {
        val animeItemList = mutableListOf<AnimeItem>()
        animeData.data.forEach { data ->
            // Assuming 'data' contains the necessary fields for AnimeItem
            val animeItem = AnimeItem(
                name = data.title,
                image = R.drawable.home_naruto,
                //image = data.images.jpg.image_url.toInt(), // Assuming 'image' is an Int representing a resource ID
                rating = data.score
            )
            animeItemList.add(animeItem)
        }
        return animeItemList
    }


     */
        init {
            val exampleAnimeList = mutableListOf<AnimeItem>()
            exampleAnimeList.add(AnimeItem(name = "Naruto", image = R.drawable.home_naruto, rating = 9.5))
            exampleAnimeList.add(AnimeItem(name = "One Piece", image = R.drawable.home_naruto, rating = 9.5))
            exampleAnimeList.add(AnimeItem(name = "Dragon Ball", image = R.drawable.home_naruto, rating = 9.5))

            deleteAllAnime()
            insertALlAnime(exampleAnimeList)
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


/*
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

 */
}











