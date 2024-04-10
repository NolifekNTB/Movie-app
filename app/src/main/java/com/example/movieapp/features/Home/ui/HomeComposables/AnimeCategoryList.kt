package com.example.movieapp.features.Home.ui.HomeComposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.core.database.entities.AnimeItemNewSeasons
import com.example.movieapp.core.database.entities.AnimeItemTopHits

@Composable
fun RowTitle(name: String, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Left
        )
        Text(
            text = "See all",
            modifier = Modifier
                .padding(end = 10.dp, top = 10.dp)
                .clickable { onClick(name) },
            fontSize = 15.sp,
            color = Color.Green)
    }
}

@Composable
fun RowItemsTopHits(
    animeList: List<AnimeItemTopHits>,
    onClick: (String, Int) -> Unit
) {
    if(animeList.isNotEmpty()){
        LazyRow(){
            items(10){ item ->
                RowItemsTopHitsElement(animeList[item]){ direction, animeId ->
                    onClick("details", animeList[item].id)
                }
            }
        }

    }
}

@Composable
fun RowItemsNewSeasons(
    animeList: List<AnimeItemNewSeasons>,
    onClick: (String, Int) -> Unit
) {
    if(animeList.isNotEmpty()){
        LazyRow(){
            items(6){ item ->
                RowItemsNewSeasonsElement(animeList[item]){ direction, animeId ->
                    onClick("details", animeList[item].id)
                }
            }
        }
    }
}

@Composable
fun RowItemsTopHitsElement(
    animeList: AnimeItemTopHits,
    onClick: (String, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .padding(10.dp)
            .clickable {
                onClick("details", animeList.id)
            }
    ){
        ElementImage(animeList.image)
        ElementCard(animeList.rating)
    }
}

@Composable
fun RowItemsNewSeasonsElement(
    animeList: AnimeItemNewSeasons,
    onClick: (String, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .padding(10.dp)
            .clickable {
                onClick("details", animeList.id)
            }
    ){
        ElementImage(animeList.image)
        ElementCard(animeList.rating)
    }
}

@Composable
fun ElementImage(image: String) {
    Card(){
        AsyncImage(
            model = image,
            contentDescription = "image",
            alpha = 0.85f,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ElementCard(rating: Double) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .size(30.dp, 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Green,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(corner = CornerSize(5.dp))
    ){
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = rating.toString(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold)
        }
    }
}