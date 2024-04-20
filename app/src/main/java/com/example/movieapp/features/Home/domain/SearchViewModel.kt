package com.example.movieapp.features.Home.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.database.entities.AnimeItemTopSearches
import com.example.movieapp.core.network.RetrofitInstance
import com.example.movieapp.core.network.models.shared.AnimeData
import com.example.movieapp.core.network.models.topSearches.topSearches
import com.example.movieapp.features.Home.data.repositories.AnimeRepository
import com.example.movieapp.features.Home.data.repositories.AnimeRepositoryTopSearches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: AnimeRepository,
    private val repoTopSearches: AnimeRepositoryTopSearches
): ViewModel() {
    suspend fun searchAllAnime(query: String): List<AnimeItemTopHits> {
        return repo.searchAnimeByName(query)
    }


    init {
        fetchApiData()
    }

    private val apiTopSearches = RetrofitInstance.animeApiTopSearches
    private fun fetchApiData(){
        viewModelScope.launch {
            if(repoTopSearches.getAllAnime().first().isEmpty()){
                val data = apiTopSearches.getTopSearches()
                Log.d("testowanie", "api data ->$data")
                repoTopSearches.insertAllAnime(mapRetroFitToRoom(data))
            }
        }
    }

    private fun mapRetroFitToRoom(dataItem: topSearches): List<AnimeItemTopSearches>{
        val animeItemList = mutableListOf<AnimeItemTopSearches>()
        dataItem.data.forEach { data ->
            val animeItem = AnimeItemTopSearches(
                name = data.entry.title,
                image = data.entry.images?.jpg?.image_url ?: "default_image_url"
            )
            animeItemList.add(animeItem)
        }
        return animeItemList
    }

    fun getTopSearches(): Flow<List<AnimeItemTopSearches>> {
        return repoTopSearches.getAllAnime()
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