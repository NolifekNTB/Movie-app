package com.example.movieapp

import DataPreview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.Home.HomeMenu.TopBar
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card

@Preview
@Composable
fun ReleaseCalendarPreview() {
    ReleaseCalendar(rememberNavController())
}

val days = listOf(
    Day("Mon", 6),
    Day("Tue", 7),
    Day("Wed", 8),
    Day("Thu", 9),
    Day("Fri", 10),
    Day("Sat", 11),
    Day("Sun", 12),
    // ...
)
val selectedDate = Day("Tue", 7)
@DataPreview
data class Day(val dayOfWeek: String, val date: Int)

@Composable
fun ReleaseCalendar(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(navController, "Release Calendar")
        CalendarBar(days, selectedDate){
            day -> //TODO
        }
        //DateList()
        //NoReleases()
    }
}

@Composable
fun CalendarBar(
    days: List<Day>,
    selectedDate: Day?,
    onDayClick: (Day) -> Unit
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(days) { day ->
            DayItem(day, day == selectedDate, onDayClick)
        }
    }
}

@Composable
fun DayItem(day: Day, isSelected: Boolean, onDayClick: (Day) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clickable { onDayClick(day) }
            .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp))
            .background(
                shape = RoundedCornerShape(4.dp),
                color = if (isSelected) Color.Green else Color.White
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = day.dayOfWeek, fontSize = 12.sp,
                    color = if (isSelected) Color.White else Color.DarkGray)
                Text(text = day.date.toString(), fontSize = 16.sp,
                    color = if (isSelected) Color.White else Color.DarkGray)
            }
        }
    }



@Composable
fun DateList() {
    TODO("Not yet implemented")
}

@Composable
fun NoReleases() {
    TODO("Not yet implemented")
}

