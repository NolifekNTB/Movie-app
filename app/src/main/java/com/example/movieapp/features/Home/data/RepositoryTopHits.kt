package com.example.movieapp.features.Home.data

import android.content.Context
import com.example.movieapp.core.database.AnimeDatabase
import com.example.movieapp.core.database.dao.AnimeDao
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepository(context: Context): AnimeDao {
    private val dao = AnimeDatabase.getInstance(context).animedao()

    override suspend fun insertAnime(anime: AnimeItemTopHits){
        dao.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItemTopHits>) {
        dao.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItemTopHits) {
        dao.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItemTopHits) {
        dao.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        dao.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItemTopHits>> {
        return dao.getAllAnime()
    }

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemTopHits> = withContext(Dispatchers.IO){
        return@withContext dao.searchAnimeByName(searchQuery)
    }
}








