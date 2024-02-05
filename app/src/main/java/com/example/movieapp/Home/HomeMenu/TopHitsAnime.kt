package com.example.movieapp.Home.HomeMenu

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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R

@Preview
@Composable
fun TopHitsAnimePreview() {
    TopHitsAnime(rememberNavController())
}

@Composable
fun TopHitsAnime(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        TopBar(navController, "Top Hits Anime")
        List()
    }
}

@Composable
fun TopBar(navController: NavController, name: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "ArrowBack",
                modifier = Modifier
                    .size(33.dp)
                    .clickable {
                        navController.popBackStack()
                    })
            Text(text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
        Icon(imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier
                .size(35.dp),
            tint = Color.Black)
    }
}

@Composable
fun List(){
    LazyColumn(){
        items(4){ item ->
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(10.dp)
                ) {
                    Card() {
                        Image(
                            painter = painterResource(id = R.drawable.attackontitan),
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
                Column(Modifier.padding(start = 15.dp, end = 25.dp)) {
                    Text(text = "Attack on Titan Final Season Part 2",
                        modifier = Modifier
                            .width(200.dp),
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "2022 | Japan")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Genre: Action fiction, Dark fantasy, Apocalyptic, Drama, Shonen, Manga",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2)
                    Spacer(modifier = Modifier.height(15.dp))

                    FilledTonalButton(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(125.dp, 40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "")
                        Text(text = "My List",
                            modifier = Modifier
                                .padding(start = 3.dp),
                            fontSize = 15.sp)
                    }
                }
            }
        }
    }
}







































