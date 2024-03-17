package com.example.movieapp.Home.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.R

@Entity(tableName = "anime")
data class AnimeItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val rating: Double
)
