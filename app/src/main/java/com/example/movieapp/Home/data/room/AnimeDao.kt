package com.example.movieapp.Home.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeItemTopHits)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeItemTopHits>)

    @Update
    suspend fun updateAnime(anime: AnimeItemTopHits)

    @Delete
    suspend fun deleteAnime(anime: AnimeItemTopHits)

    @Query("DELETE FROM anime")
    suspend fun deleteALlAnime()

    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeItemTopHits>>

    @Query("SELECT * FROM anime WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemTopHits>
}

@Dao
interface AnimeDaoNewSeasons {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeItemNewSeasons)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeItemNewSeasons>)

    @Update
    suspend fun updateAnime(anime: AnimeItemNewSeasons)

    @Delete
    suspend fun deleteAnime(anime: AnimeItemNewSeasons)

    @Query("DELETE FROM animeNewSeasons")
    suspend fun deleteALlAnime()

    @Query("SELECT * FROM animeNewSeasons")
    fun getAllAnime(): Flow<List<AnimeItemNewSeasons>>

    @Query("SELECT * FROM animeNewSeasons WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemNewSeasons>
}