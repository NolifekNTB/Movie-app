package com.example.movieapp.Home.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val image: Int,
    val rating: Double
)
