package com.example.movieapp.Profile.ui.ProfileScreens.Subscribe.Payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.Profile.logic.ProfileViewModel

@Composable
fun CircleButton(name: String, viewModel: ProfileViewModel){
    val clicked = viewModel.clickedDay.collectAsState().value

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .size(20.dp)
            .clickable {
                viewModel.whichMethodIsClicked(name)
            },
        colors = CardDefaults.cardColors(
            containerColor = if(clicked == name) Color.Green else Color.White
        ),
        border = BorderStroke(2.dp, Color.Green)
    ){
        Text(text = "", color = Color.White, modifier = Modifier.padding(1.dp))
    }
}




