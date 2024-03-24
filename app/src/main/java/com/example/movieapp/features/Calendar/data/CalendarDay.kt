package com.example.movieapp.features.Calendar.data

data class Day(
    val dayOfWeek: String,
    val date: Int,
    var isSelected: Boolean = false
)