package com.example.movieapp.features.Calendar.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.features.Calendar.data.Day
import com.example.movieapp.features.Calendar.domain.CalendarViewModel
import com.example.movieapp.shared.TopBar

@Composable
fun ReleaseCalendar(onClick: () -> Unit) {
    val viewModel = hiltViewModel<CalendarViewModel>()
    val days = viewModel.daysOfTheWeek.collectAsState().value

    if(days.isNotEmpty()){
        Log.d("testowanie", days[2].toString())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar("Release Calendar"){_ -> onClick()}
        CalendarBar(days = days, viewModel = viewModel)
        DateList(viewModel)
        //NotFound("No Release Schedule", "Sorry, there is no anime release schedule on this date.")
    }
}

@Composable
fun CalendarBar(days: List<Day>, viewModel: CalendarViewModel) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(days) { day ->
            DayItem(day, viewModel)
        }
    }
}

@Composable
fun DateList(viewModel: CalendarViewModel) {
    val toDisplay = viewModel.clickedDay.collectAsState().value

    if(toDisplay != null){
        LazyColumn(){
            items(toDisplay.animeItems.size){ element ->
                dateListItem(toDisplay.animeItems[element], element)
            }
        }
    }
}

















