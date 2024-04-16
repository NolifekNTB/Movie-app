package com.example.movieapp.features.Home.data.repositories

import android.content.Context
import com.example.movieapp.core.database.AnimeDatabase
import com.example.movieapp.core.database.dao.AnimeDaoNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepositoryNewSeasons(context: Context): AnimeDaoNewSeasons {
    private val daoNewSeasons = AnimeDatabase.getInstance(context).animedaoNewSeasons()

    override suspend fun insertAnime(anime: AnimeItemTopCharacters){
        daoNewSeasons.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItemTopCharacters>) {
        daoNewSeasons.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItemTopCharacters) {
        daoNewSeasons.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItemTopCharacters) {
        daoNewSeasons.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        daoNewSeasons.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItemTopCharacters>> {
        return daoNewSeasons.getAllAnime()
    }

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemTopCharacters> =
            withContext(Dispatchers.IO)
        {
        return@withContext daoNewSeasons.searchAnimeByName(searchQuery)
    }
}



