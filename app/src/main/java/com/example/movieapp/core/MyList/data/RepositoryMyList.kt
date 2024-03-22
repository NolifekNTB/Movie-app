package com.example.movieapp.core.MyList.data

import android.content.Context
import com.example.movieapp.Home.data.room.newSeasons.AnimeDaoNewSeasons
import com.example.movieapp.Home.data.room.newSeasons.AnimeItemNewSeasons
import com.example.movieapp.core.AnimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimeRepositoryMyList(context: Context): AnimeDaoMyList {
    private val daoMyList = AnimeDatabase.getInstance(context).animedaoMyList()

    override suspend fun insertAnime(anime: AnimeItemMyList){
        daoMyList.insertAnime(anime)
    }

    override suspend fun insertAllAnime(animeList: List<AnimeItemMyList>) {
        daoMyList.insertAllAnime(animeList)
    }

    override suspend fun updateAnime(anime: AnimeItemMyList) {
        daoMyList.updateAnime(anime)
    }

    override suspend fun deleteAnime(anime: AnimeItemMyList) {
        daoMyList.deleteAnime(anime)
    }

    override suspend fun deleteALlAnime() {
        daoMyList.deleteALlAnime()
    }

    override fun getAllAnime(): Flow<List<AnimeItemMyList>> {
        return daoMyList.getAllAnime()
    }

    override suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemMyList> = withContext(Dispatchers.IO) {
        return@withContext daoMyList.searchAnimeByName(searchQuery)
    }
}
