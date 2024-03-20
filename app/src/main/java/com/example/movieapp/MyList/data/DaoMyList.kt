package com.example.movieapp.MyList.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.Home.data.room.topHits.AnimeItemTopHits
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDaoMyList {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeItemMyList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeItemMyList>)

    @Update
    suspend fun updateAnime(anime: AnimeItemMyList)

    @Delete
    suspend fun deleteAnime(anime: AnimeItemMyList)

    @Query("DELETE FROM animeMyList")
    suspend fun deleteALlAnime()

    @Query("SELECT * FROM animeMyList")
    fun getAllAnime(): Flow<List<AnimeItemMyList>>

    @Query("SELECT * FROM animeMyList WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchAnimeByName(searchQuery: String): List<AnimeItemMyList>
}