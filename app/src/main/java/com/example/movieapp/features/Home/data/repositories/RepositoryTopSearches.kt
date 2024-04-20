package com.example.movieapp.features.Home.data.repositories

import android.content.Context
import com.example.movieapp.core.database.AnimeDatabase
import com.example.movieapp.core.database.dao.AnimeDaoTopSearches
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.database.entities.AnimeItemTopSearches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepositoryTopSearches(context: Context): AnimeDaoTopSearches {
    private val dao = AnimeDatabase.getInstance(context).animedaoTopSearches()
    override suspend fun insertAnime(anime: AnimeItemTopSearches) {
        dao.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItemTopSearches>) {
        dao.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItemTopSearches) {
        dao.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItemTopSearches) {
        dao.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        dao.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItemTopSearches>> {
       return dao.getAllAnime()
    }
}