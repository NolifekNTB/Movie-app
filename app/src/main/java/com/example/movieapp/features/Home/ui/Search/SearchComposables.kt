package com.example.movieapp.features.Home.ui.Search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Composable
fun choseFilter(name: String) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.padding(3 .dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green
        ),
        border = BorderStroke(2.dp, Color.Green)
    ) {
        Text(
            text = name,
            color = Color.White
        )
    }
}

@Composable
fun SearchScreenDefault() {
    Column(
        modifier = Modifier.height(70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "Top Searches",
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold)
    }
    LazyColumn(){
        items(5)
        {
            SearchScreenDefaultItem()
        }
    }
}

@Composable
fun SearchScreenDefaultItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(end = 20.dp, start = 20.dp, top = 10.dp)
    ){
        Card(
            modifier = Modifier.size(150.dp, 125.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.home_attackontitan),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(15.dp))
        Text(
            text = "Attack on titan Final Season part 2",
            modifier = Modifier
                .width(160.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NotFound(title: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.search_error),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp))
        Text(
            text = title,
            color = Color.Green,
            fontSize = 25.sp,
            fontWeight = FontWeight.W500
        )
        Text(
            text = text,
            modifier = Modifier
                .width(350.dp)
                .padding(15.dp),
            fontSize = 15.sp,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )

    }
}

