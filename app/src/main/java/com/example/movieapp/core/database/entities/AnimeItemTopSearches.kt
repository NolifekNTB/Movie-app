package com.example.movieapp.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.core.network.models.shared.Genre
import com.example.movieapp.core.network.models.shared.Trailer

@Entity(tableName = "animeTopSearches")
data class AnimeItemTopSearches(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
)