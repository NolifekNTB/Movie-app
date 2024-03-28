package com.example.movieapp.features.Calendar.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Calendar.data.Day
import com.example.movieapp.features.Calendar.domain.CalendarViewModel
import java.time.LocalTime

@Composable
fun DayItem(day: Day, viewModel: CalendarViewModel) {
    val whichDayIsSelected = viewModel.clickedDay.collectAsState().value
    val isSelected = whichDayIsSelected == day

    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clickable { viewModel.whichDayIsClicked(day) },
        contentAlignment = Alignment.Center
    ) {
        Card(shape = RoundedCornerShape(15.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) Color.Green else Color.White
            )
        ){
            Column(modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = day.dayOfWeek, fontSize = 12.sp,
                    color = if (isSelected) Color.White else Color.DarkGray)
                Text(text = day.date.toString(), fontSize = 16.sp,
                    color = if (isSelected) Color.White else Color.DarkGray)
            }
        }
    }
}

@Composable
fun dateListItem(topHitsElement: AnimeItemTopHits, element: Int) {
    upperTime(element)
    itemMainContent(topHitsElement)
    currentTimeDivider(topHitsElement, element)
}

@Composable
fun upperTime(element: Int){
    val currentTime = LocalTime.now()

    Column(
        modifier = Modifier
            .padding(start = 15.dp, top = 5.dp)
    ){
        Text(
            text = "${currentTime.hour+element}:${currentTime.minute}",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun itemMainContent(topHitsElement: AnimeItemTopHits){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Card(){
            AsyncImage(
                model = topHitsElement.image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp, 125.dp)
            )
        }
        Column(
            modifier = Modifier.padding(top = 10.dp, start = 20.dp)
        ) {
            Text(text = topHitsElement.name,
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
                        containerColor = Color.Transparent,
                        contentColor = Color.White
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

@Composable
fun currentTimeDivider(topHitsElement: AnimeItemTopHits, element: Int){
    val currentTime = LocalTime.now()

    if(element == 1){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp)
        ){
            Divider(
                thickness = 3.dp,
                color = Color.Green,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp))
            )
            Text(
                text = "Current time - ${currentTime.hour}:${currentTime.minute}",
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontStyle = FontStyle.Italic
            )
            Divider(
                thickness = 3.dp,
                color = Color.Green,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
    }
}














