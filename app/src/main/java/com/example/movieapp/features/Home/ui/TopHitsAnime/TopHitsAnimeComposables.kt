package com.example.movieapp.features.Home.ui.TopHitsAnime

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Composable
fun TopHitsAnimeListImage(item: Int) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(10.dp)
    ) {
        Card() {
            Image(
                painter = painterResource(id = R.drawable.home_attackontitan),
                contentDescription = "mainPhoto",
                alpha = 0.85f
            )
        }
        Card(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp)
                .size(30.dp, 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(corner = CornerSize(5.dp))
        ) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "9.8",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Text(
            text = "$item",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 10.dp, start = 10.dp),
            textAlign = TextAlign.Left,
            fontSize = 33.sp,
            color = Color.White
        )
    }
}

@Composable
fun TopHitsAnimeListDetails() {
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 25.dp)
    ) {
        Text(
            text = "Attack on Titan Final Season Part 2",
            modifier = Modifier
                .width(200.dp),
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "2022 | Japan")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Genre: Action fiction, Dark fantasy, Apocalyptic, " +
                    "Drama, Shonen, Manga",
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(15.dp))

        FilledTonalButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(125.dp, 40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "")
            Text(
                text = "My List",
                modifier = Modifier
                    .padding(start = 3.dp),
                fontSize = 15.sp)
        }
    }
}















