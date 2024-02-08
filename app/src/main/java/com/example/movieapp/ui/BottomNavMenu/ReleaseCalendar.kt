package com.example.movieapp.ui.BottomNavMenu

import DataPreview
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.Home.HomeMenu.TopBar
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.movieapp.R

@Preview
@Composable
fun ReleaseCalendarPreview() {
    ReleaseCalendar(rememberNavController())
}

val days = listOf(
    Day("Mon", 4),
    Day("Mon", 5),
    Day("Mon", 6),
    Day("Tue", 7),
    Day("Wed", 8),
    Day("Thu", 9),
    Day("Fri", 10),
    Day("Sat", 11),
    Day("Sun", 12),
    // ...
)
@DataPreview
data class Day(val dayOfWeek: String, val date: Int)

@Composable
fun ReleaseCalendar(navController: NavController) {
    val selectedDate by mutableStateOf<Day?>(Day("Tue", 7))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(navController, "Release Calendar")
        CalendarBar(days, selectedDate){
            day -> //TODO
        }
        DateList()
        //NotFound("No Release Schedule", "Sorry, there is no anime release schedule on this date.")
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
            .clickable { onDayClick(day); },
        contentAlignment = Alignment.Center
    ) {
        Card(shape = RoundedCornerShape(15.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) Color.Green else Color.White
            )
        ){
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
    }



@Composable
fun DateList() {
    LazyColumn(){
        items(5){
            Column(Modifier.padding(start = 15.dp, top = 5.dp)){
                Text(text = "12:00", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){
                Card( modifier = Modifier
                    .size(150.dp, 125.dp)){
                    Image(painter = painterResource(id = R.drawable.attackontitan),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth)
                }
                Column(Modifier.padding(top = 10.dp, start = 10.dp)) {
                    Text(text = "Attack on titan",
                        modifier = Modifier
                            .width(125.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(text = "Episodes 1040")
                    Spacer(Modifier.height(5.dp))
                    Card(
                        modifier = Modifier
                            .size(90.dp, 30.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(10.dp))
                    ) {
                        OutlinedButton(
                            onClick = {  },
                            modifier = Modifier.padding(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color.Green
                            ),
                            border = BorderStroke(3.dp, Color.Green),
                            contentPadding = PaddingValues(3.dp)
                        ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Spacer(Modifier.width(5.dp))
                                Text(text = "My List")
                            }
                        }
                }
            }
        }
    }
}

















