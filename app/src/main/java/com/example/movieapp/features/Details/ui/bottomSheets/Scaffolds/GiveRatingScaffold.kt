package com.example.movieapp.features.Details.ui.bottomSheets.Scaffolds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.movieapp.features.Details.ui.bottomSheets.ScaffoldSheetButtons
import com.example.movieapp.features.Details.ui.bottomSheets.ScaffoldTitle
import com.example.movieapp.shared.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiveRatingBox(scaffoldState: BottomSheetScaffoldState, viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .height(400.dp)
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScaffoldTitle(title = "Give Rating")
        Divider(thickness = 1.dp, color = Color.LightGray)
        RatingContent()
        Divider(thickness = 1.dp, color = Color.LightGray)
        StarsRow()
        Spacer(modifier = Modifier.height(15.dp))
        ScaffoldSheetButtons(scaffoldState = scaffoldState, viewModel = viewModel, "rating")
    }
}

@Composable
fun RatingContent(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp)
    ) {
        RatingNumberDisplay()
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        RatingChart()
    }
}

@Composable
fun RatingNumberDisplay(modifier : Modifier = Modifier) {
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
        RatingStarsSmall()
        Text(text = "(31,123 users)", color = Color.DarkGray)
    }
}

@Composable
fun RatingStarsSmall() {
    Row {
        repeat(5) {
            Icon(
                Icons.Default.Star,
                contentDescription = "Star",
                modifier = Modifier.size(20.dp),
                tint = Color.Green
            )
        }
    }
}

@Composable
fun RatingChart() {
    Column {
        (5 downTo 1).forEach { stars ->
            RatingProgressBar(stars, 0.8f)
        }
    }
}

@Composable
fun RatingProgressBar(stars: Int, progress: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$stars stars")
        Spacer(Modifier.width(10.dp))
        LinearProgressIndicator(
            progress = progress,
            color = Color.Green,
            trackColor = Color.Gray,
            modifier = Modifier
                .height(18.dp)
                .clip(RoundedCornerShape(5.dp))
                .weight(1f)
        )
    }
}

@Composable
fun StarsRow(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = Color.Green,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 15.dp)
            )
        }
    }
}