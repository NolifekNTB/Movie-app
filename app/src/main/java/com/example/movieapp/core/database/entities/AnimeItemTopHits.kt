package com.example.movieapp.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeItemTopHits(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val rating: Double
)