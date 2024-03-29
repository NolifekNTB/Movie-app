package com.example.movieapp.features.Home.data

import android.content.Context
import com.example.movieapp.core.database.AnimeDatabase
import com.example.movieapp.core.database.dao.AnimeDaoNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepositoryNewSeasons(context: Context): AnimeDaoNewSeasons {
    private val daoNewSeasons = AnimeDatabase.getInstance(context).animedaoNewSeasons()

    override suspend fun insertAnime(anime: AnimeItemNewSeasons){
        daoNewSeasons.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItemNewSeasons>) {
        daoNewSeasons.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItemNewSeasons) {
        daoNewSeasons.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItemNewSeasons) {
        daoNewSeasons.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        daoNewSeasons.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItemNewSeasons>> {
        return daoNewSeasons.getAllAnime()
    }

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemNewSeasons> = withContext(
        Dispatchers.IO){
        return@withContext daoNewSeasons.searchAnimeByName(searchQuery)
    }
}



