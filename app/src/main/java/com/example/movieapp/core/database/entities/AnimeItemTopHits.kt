package com.example.movieapp.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.core.network.models.shared.Trailer
import com.example.movieapp.core.network.models.shared.Genre

@Entity(tableName = "anime")
data class AnimeItemTopHits(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val rating: Double,
    val year: Int,
    val genres: List<Genre>,
    val description: String,
    val trailer: Trailer
)