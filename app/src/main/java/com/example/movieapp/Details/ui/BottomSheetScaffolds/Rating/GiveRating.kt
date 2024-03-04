package com.example.movieapp.Details.ui.BottomSheetScaffolds.Rating

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.Details.logic.DetailsViewModel
import com.example.movieapp.Details.ui.BottomSheetScaffolds.scaffoldSheetButtons
import com.example.movieapp.Details.ui.BottomSheetScaffolds.shareTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun giveRatingBox(scaffoldState: BottomSheetScaffoldState) {
    val viewModel = DetailsViewModel()

    Column(
        modifier = Modifier
            .height(400.dp)
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shareTitle(title = "Give Rating")
        Divider(thickness = 1.dp, color = Color.LightGray)
        mainContentRating()
        Divider(thickness = 1.dp, color = Color.LightGray)
        stars()
        Spacer(modifier = Modifier.height(15.dp))
        scaffoldSheetButtons(scaffoldState = scaffoldState, viewModel = viewModel, "rating")
    }
}

@Composable
fun mainContentRating(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp)
    ) {
        mainContentRatingNumber()
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        mainContentRatingChart(Modifier.weight(1f))
    }
}

@Composable
fun stars(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
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