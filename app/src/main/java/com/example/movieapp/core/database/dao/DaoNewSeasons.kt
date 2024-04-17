package com.example.movieapp.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.core.database.entities.AnimeItemTopCharacters
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDaoTopCharacters {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeItemTopCharacters)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeItemTopCharacters>)

    @Update
    suspend fun updateAnime(anime: AnimeItemTopCharacters)

    @Delete
    suspend fun deleteAnime(anime: AnimeItemTopCharacters)

    @Query("DELETE FROM animeTopCharacters")
    suspend fun deleteALlAnime()

    @Query("SELECT * FROM animeTopCharacters")
    fun getAllAnime(): Flow<List<AnimeItemTopCharacters>>

    @Query("SELECT * FROM animeTopCharacters WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemTopCharacters>
}