package com.example.movieapp.features.Calendar.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalendarViewModel: ViewModel() {
    private val daysOfTheWeek = listOf(
        com.example.movieapp.features.Calendar.data.Day("Mon", 6),
        com.example.movieapp.features.Calendar.data.Day("Tue", 7),
        com.example.movieapp.features.Calendar.data.Day("Wed", 8),
        com.example.movieapp.features.Calendar.data.Day("Thu", 9),
        com.example.movieapp.features.Calendar.data.Day("Fri", 10),
        com.example.movieapp.features.Calendar.data.Day("Sat", 11),
        com.example.movieapp.features.Calendar.data.Day("Sun", 12),
        com.example.movieapp.features.Calendar.data.Day("Mon", 13),
        com.example.movieapp.features.Calendar.data.Day("Tue", 14),
        com.example.movieapp.features.Calendar.data.Day("Wed", 15),
        com.example.movieapp.features.Calendar.data.Day("Thu", 16),
        com.example.movieapp.features.Calendar.data.Day("Fri", 17),
        com.example.movieapp.features.Calendar.data.Day("Sat", 18),
        com.example.movieapp.features.Calendar.data.Day("Sun", 19),
        )
    fun getDays(): List<com.example.movieapp.features.Calendar.data.Day> {
        return daysOfTheWeek
    }

    private var _clickedDay = MutableStateFlow(
        com.example.movieapp.features.Calendar.data.Day(
            "Thu",
            9
        )
    )
    var clickedDay = _clickedDay.asStateFlow()

    fun whichDayIsClicked(day: com.example.movieapp.features.Calendar.data.Day) {
        _clickedDay.value = day
    }
}