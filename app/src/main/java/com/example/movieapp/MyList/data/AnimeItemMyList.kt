package com.example.movieapp.MyList.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animeMyList")
data class AnimeItemMyList(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val rating: Double
)