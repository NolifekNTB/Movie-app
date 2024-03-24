package com.example.movieapp.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.core.database.entities.AnimeItemTopHits
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