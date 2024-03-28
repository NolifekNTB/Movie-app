package com.example.movieapp.features.Calendar.domain

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.network.RetrofitInstance
import com.example.movieapp.features.Calendar.data.Day
import com.example.movieapp.features.Home.data.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repoTopHits: AnimeRepository
) : ViewModel(){
    //Clicked day section
    private var _clickedDay = MutableStateFlow<Day?>(null)
    var clickedDay = _clickedDay.asStateFlow()

    fun whichDayIsClicked(day: Day) {
        _clickedDay.value = day
    }

    //Data from room
    private val animeTopHits: Flow<List<AnimeItemTopHits>> = repoTopHits.getAllAnime()

    private fun transformAnimeData(animeTopHits: Flow<List<AnimeItemTopHits>>): Flow<List<Day>> {
        return animeTopHits.map { animeList ->
            animeList.chunked(3).mapIndexed { index, chunk ->
                Day(
                    dayOfWeek = generateDaysOfWeek(index), // Placeholder for actual day of week calculation
                    date = index, // Placeholder for actual date calculation
                    isSelected = false, // Default value
                    animeItems = chunk
                )
            }
        }
    }

    //Generated days section
    private val _daysOfTheWeek = MutableStateFlow<List<Day>>(emptyList())
    val daysOfTheWeek: StateFlow<List<Day>> = _daysOfTheWeek.asStateFlow()

    private fun transformAndAssignAnimeData() {
        viewModelScope.launch {
            transformAnimeData(animeTopHits).collect { transformedDays ->
                _daysOfTheWeek.value = transformedDays

                if (transformedDays.isNotEmpty() && _clickedDay.value == null) {
                    _clickedDay.value = transformedDays[3]
                }
            }
        }
    }

    init {
        transformAndAssignAnimeData()
    }

    fun generateDaysOfWeek(index: Int): String {
        return when(index) {
            0 -> return "Sun"
            1 -> return "Mon"
            2 -> return "Tue"
            3 -> return "Wed"
            4 -> return "Thu"
            5 -> return "Fri"
            6 -> return "Sat"
            7 -> return "Sun"
            8 -> return "Mon"
            9 -> return "Tue"
            10 -> return "Wed"
            11 -> return "Thu"
            else -> ""
        }
    }
}















