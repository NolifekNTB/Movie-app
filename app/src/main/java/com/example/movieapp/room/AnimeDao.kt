package com.example.movieapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.data.AnimeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeItem>)

    @Update
    suspend fun updateAnime(anime: AnimeItem)

    @Delete
    suspend fun deleteAnime(anime: AnimeItem)

    @Query("DELETE FROM anime")
    suspend fun deleteALlAnime()

    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeItem>>

    @Query("SELECT * FROM anime WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchAnimeByName(searchQuery: String): List<AnimeItem>
}