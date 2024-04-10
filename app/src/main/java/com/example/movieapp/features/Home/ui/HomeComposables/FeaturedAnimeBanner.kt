package com.example.movieapp.features.Home.ui.HomeComposables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.shared.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val bannerImageHeight = 300.dp
private val ButtonWidth = 100.dp
private val ButtonHeight = 35.dp

@Composable
fun BannerImage(){
    Image(
        painter = painterResource(id = R.drawable.home_demonslayer),
        contentDescription = "mainPhoto",
        modifier = Modifier.height(bannerImageHeight),
        contentScale = ContentScale.Crop,
        alpha = 0.85f
    )
}

@Composable
fun SearchAndNotifications(onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row {
            SearchAndNotificationsIcons { direction ->
                onClick(direction)
            }
        }
    }
}

@Composable
fun PlayAndMyList(sharedViewModel: SharedViewModel, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .height(bannerImageHeight)
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        PlayAndMyListElements(sharedViewModel){ direction ->
            onClick(direction)
        }
    }
}

@Composable
fun SearchAndNotificationsIcons(onClick: (String) -> Unit) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search",
        modifier = Modifier
            .size(35.dp)
            .clickable {
                onClick("Search")
            },
        tint = Color.White
    )
    Icon(
        imageVector = Icons.Outlined.Notifications,
        contentDescription = "Notification",
        modifier = Modifier
            .size(35.dp)
            .clickable {
                onClick("Notification")
            },
        tint = Color.White
    )
}

@Composable
fun PlayAndMyListElements(sharedViewModel: SharedViewModel, onClick: (String) -> Unit) {
    Text(
        text = "Demon slayer: Kimetsu no Yaiba",
        modifier = Modifier
            .size(275.dp, 40.dp),
        color = Color.White,
        fontSize = 25.sp,
        fontWeight = FontWeight.W500,
        fontFamily = FontFamily.Default,
        overflow = TextOverflow.Ellipsis
    )
    Text(
        text = "Action: Shounen, Martial Arts, Adventure",
        color = Color.White,
        fontWeight = FontWeight.W500,
        fontFamily = FontFamily.Default,
        overflow = TextOverflow.Ellipsis
    )
    Row (modifier = Modifier.padding(top = 10.dp)) {
        PlayButton(){onClick("webView")}
        Spacer(modifier = Modifier.width(10.dp))
        MyListButton(sharedViewModel)
    }
}

@Composable
fun PlayButton(onClick: (String) -> Unit) {
    FilledTonalButton(
        onClick = { onClick("webView") },
        modifier = Modifier
            .size(ButtonWidth, ButtonHeight),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = ""
        )
        Text(
            text = "Play",
            modifier = Modifier
                .padding(start = 3.dp),
            fontSize = 15.sp
        )
    }
}

@Composable
fun MyListButton(sharedViewModel: SharedViewModel) {
    val animeItem = AnimeItemMyList(
        id = 0,
        name = "Demon slayer: Kimetsu no Yaiba",
        image = "https://m.media-amazon.com/images/I/71bWTsDKuGL._AC_UF894,1000_QL80_.jpg",
        rating = 8.2
    )

    var isCheck by remember { mutableStateOf(false) }
    var item = AnimeItemMyList(0, "", "", 0.0)
    LaunchedEffect(Unit) {
        isCheck = sharedViewModel.searchAllAnime("Demon Slayer").isNotEmpty()
    }

    OutlinedButton(
        onClick = {
            if (!isCheck) {
                sharedViewModel.insertAnimeItem(animeItem)
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    item = sharedViewModel.searchAllAnime("Demon Slayer").first()
                    sharedViewModel.deleteAnimeItem(item)
                }
            }
            isCheck = !isCheck
        },
        modifier = Modifier
            .size(ButtonWidth, ButtonHeight),
        border = BorderStroke(if(!isCheck) 2.dp else 0.dp, Color.White),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if(!isCheck) Color.Transparent else Color.Green,
            contentColor = if(!isCheck) Color.White else Color.White
        ),
        contentPadding = PaddingValues(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ""
        )
        Text("My list")
    }
}