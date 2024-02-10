package com.example.movieapp.room

import android.content.Context
import com.example.movieapp.data.AnimeItem
import kotlinx.coroutines.flow.Flow

class AnimeRepository(context: Context): AnimeDao {
    private val dao = AnimeDatabase.getInstance(context).animedao()

    override suspend fun insertAnime(anime: AnimeItem) {
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
}