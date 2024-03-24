package com.example.movieapp.features.Home.ui.Search.domain

import androidx.lifecycle.ViewModel
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.data.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: AnimeRepository
): ViewModel() {

    suspend fun searchAllAnime(query: String): List<AnimeItemTopHits> {
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