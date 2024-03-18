package com.example.movieapp.Home.data.room

import android.content.Context
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

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemNewSeasons> = withContext(Dispatchers.IO){
        return@withContext daoNewSeasons.searchAnimeByName(searchQuery)
    }
}












