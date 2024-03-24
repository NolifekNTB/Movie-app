package com.example.movieapp.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import kotlinx.coroutines.flow.Flow

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