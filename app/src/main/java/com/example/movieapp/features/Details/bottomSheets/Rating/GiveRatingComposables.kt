package com.example.movieapp.features.Details.bottomSheets.Rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun mainContentRatingNumber(modifier : Modifier = Modifier) {
    Column(modifier = modifier.padding(end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                text = "9.8",
                fontSize = 55.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "/10",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }
        Row(){
            repeat(5){
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = Color.Green,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Text(text = "(31,123 users)", color = Color.DarkGray)
    }
}

@Composable
fun mainContentRatingChart(modifier : Modifier = Modifier) {
    Column(modifier) {
        for(i in 5 downTo 1){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(
                    start = 15.dp, end = 5.dp,
                    top = 5.dp, bottom = 5.dp
                )
            ){
                Text(text = "$i")
                Spacer(modifier = Modifier.width(5.dp))
                LinearProgressIndicator(
                    progress = 0.8f,
                    color = Color.Green,
                    trackColor = Color.Gray,
                    modifier = Modifier.clip(RoundedCornerShape(5.dp))
                )
            }
        }
    }
}

