package com.example.movieapp.Home.data

import android.content.Context
import com.example.movieapp.Home.logic.room.AnimeDao
import com.example.movieapp.Home.logic.room.AnimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepository(context: Context): AnimeDao {
    private val dao = AnimeDatabase.getInstance(context).animedao()

    override suspend fun insertAnime(anime: AnimeItem){
        dao.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItem>) {
        dao.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItem) {
        dao.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItem) {
        dao.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        dao.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItem>> {
        return dao.getAllAnime()
    }

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItem> = withContext(Dispatchers.IO){
        return@withContext dao.searchAnimeByName(searchQuery)
    }
}