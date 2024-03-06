package com.example.movieapp.Calendar.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.movieapp.Calendar.data.Day
import com.example.movieapp.Calendar.logic.CalendarViewModel
import com.example.movieapp.Home.ui.HomeScreens.Search.NotFound
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar

@Composable
fun ReleaseCalendar(onClick: () -> Unit) {
    val viewModel = CalendarViewModel()
    val days = viewModel.getDays()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar("Release Calendar", onClick)
        CalendarBar(days, viewModel)
        DateList()
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
fun DateList() {
    LazyColumn(){
        items(5){ index ->
            dateListItem(index)
        }
    }
}

















