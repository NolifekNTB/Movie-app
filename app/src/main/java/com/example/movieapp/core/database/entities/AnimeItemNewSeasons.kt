package com.example.movieapp.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animeNewSeasons")
data class AnimeItemNewSeasons(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val rating: Double
)
