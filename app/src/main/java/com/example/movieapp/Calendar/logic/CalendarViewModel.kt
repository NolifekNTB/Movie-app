package com.example.movieapp.Calendar.logic

import androidx.lifecycle.ViewModel
import com.example.movieapp.Calendar.data.Day
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalendarViewModel: ViewModel() {
    private val daysOfTheWeek = listOf(
        Day("Mon", 6),
        Day("Tue", 7),
        Day("Wed", 8),
        Day("Thu", 9),
        Day("Fri", 10),
        Day("Sat", 11),
        Day("Sun", 12),
        Day("Mon", 13),
        Day("Tue", 14),
        Day("Wed", 15),
        Day("Thu", 16),
        Day("Fri", 17),
        Day("Sat", 18),
        Day("Sun", 19),
        )
    fun getDays(): List<Day> {
        return daysOfTheWeek
    }

    private var _clickedDay = MutableStateFlow(Day("Thu", 9))
    var clickedDay = _clickedDay.asStateFlow()

    fun whichDayIsClicked(day: Day) {
        _clickedDay.value = day
    }
}