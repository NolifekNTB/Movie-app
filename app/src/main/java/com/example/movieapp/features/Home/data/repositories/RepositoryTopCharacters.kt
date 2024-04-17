package com.example.movieapp.features.Home.data.repositories

import android.content.Context
import com.example.movieapp.core.database.AnimeDatabase
import com.example.movieapp.core.database.dao.AnimeDaoTopCharacters
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepositoryTopCharacters(context: Context): AnimeDaoTopCharacters {
    private val daoTopCharacters = AnimeDatabase.getInstance(context).animedaoTopCharacters()

    override suspend fun insertAnime(anime: AnimeItemTopCharacters){
        daoTopCharacters.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItemTopCharacters>) {
        daoTopCharacters.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItemTopCharacters) {
        daoTopCharacters.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItemTopCharacters) {
        daoTopCharacters.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        daoTopCharacters.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItemTopCharacters>> {
        return daoTopCharacters.getAllAnime()
    }

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemTopCharacters> =
            withContext(Dispatchers.IO)
        {
        return@withContext daoTopCharacters.searchAnimeByName(searchQuery)
    }
}



