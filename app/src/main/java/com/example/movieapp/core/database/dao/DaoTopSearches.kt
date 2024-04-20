package com.example.movieapp.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.core.database.entities.AnimeItemTopSearches
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDaoTopSearches {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeItemTopSearches)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeItemTopSearches>)

    @Update
    suspend fun updateAnime(anime: AnimeItemTopSearches)

    @Delete
    suspend fun deleteAnime(anime: AnimeItemTopSearches)

    @Query("DELETE FROM animeTopSearches")
    suspend fun deleteALlAnime()

    @Query("SELECT * FROM animeTopSearches")
    fun getAllAnime(): Flow<List<AnimeItemTopSearches>>
}