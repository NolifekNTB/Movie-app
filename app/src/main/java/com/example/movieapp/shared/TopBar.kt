package com.example.movieapp.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
                            //GOAL OF THIS FUNCTION
            TopBar is used in TopHitsAnime, Notification, NewEpisodeReleases,
              SortFilter and ReleaseCalendar, videoPlayer, MyList, Profile,
                 Subscribe, payment, addNewCard, reviewSummary
 */
@Composable
fun TopBar(name: String, onClick: (String) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(15.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        TopBarTitle(name) {what ->
            onClick(what)
        }
        TopBarActionIcon(name){what ->
            onClick(what)
        }
    }
}

@Composable
fun TopBarTitle(name: String, onClick: (String) -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)){
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "ArrowBack",
            modifier = Modifier
                .size(33.dp)
                .clickable {
                    onClick("Back")
                })
        Text(text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun TopBarActionIcon(name: String, onClick: (String) -> Unit){
    val icon = when (name) {
        "Top Hits Anime"-> Icons.Default.Search
        "My List"-> Icons.Default.Search
        "Download"-> Icons.Default.Search
        "New Episode Releases"-> Icons.Default.Search
        "Sort & Filter" -> Icons.Default.Add
        else -> Icons.Default.MoreVert
    }

    IconButton(onClick = { onClick("Icon") }) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (name == "Sort & Filter" || name == "ReviewSummary") Color.White
            else if (name == "") Color.White
            else Color.Black,
            modifier = Modifier.size(35.dp)
        )
    }
}
