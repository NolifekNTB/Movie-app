package com.example.movieapp.features.Calendar.ui

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
import com.example.movieapp.shared.TopBar

@Composable
fun ReleaseCalendar(onClick: () -> Unit) {
    val viewModel = com.example.movieapp.features.Calendar.domain.CalendarViewModel()
    val days = viewModel.getDays()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar("Release Calendar"){_ -> onClick()}
        CalendarBar(days, viewModel)
        DateList()
        //NotFound("No Release Schedule", "Sorry, there is no anime release schedule on this date.")
    }
}

@Composable
fun CalendarBar(days: List<com.example.movieapp.features.Calendar.data.Day>, viewModel: com.example.movieapp.features.Calendar.domain.CalendarViewModel) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(days) { day ->
            DayItem(day, viewModel)
        }
    }
}

@Composable
fun DateList() {
    LazyColumn(){
        items(5){ index ->
            dateListItem(index)
        }
    }
}

















