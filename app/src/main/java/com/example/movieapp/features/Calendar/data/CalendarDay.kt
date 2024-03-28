package com.example.movieapp.features.Calendar.data

import com.example.movieapp.core.database.entities.AnimeItemTopHits
import java.time.DayOfWeek
import java.time.LocalDate

data class Day(
    val dayOfWeek: String,
    val date: Int,
    var isSelected: Boolean = false,
    val animeItems: List<AnimeItemTopHits> = emptyList()
)

