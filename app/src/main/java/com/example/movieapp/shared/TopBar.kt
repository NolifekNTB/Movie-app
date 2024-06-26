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
import androidx.compose.ui.graphics.vector.ImageVector
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
fun TopBar(title: String, onNavigate: (String) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(15.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        TopBarBackButton() { direction ->
            onNavigate(direction)
        }
        Text(text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        TopBarActionIcon(title){ direction ->
            onNavigate(direction)
        }
    }
}

@Composable
fun TopBarBackButton(onClick: (String) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "ArrowBack",
            modifier = Modifier
                .size(33.dp)
                .clickable {
                    onClick("Back")
                }
        )
    }
}

@Composable
fun TopBarActionIcon(screenName: String, onClick: (String) -> Unit){
    val icon = getTopBarIconForScreen(screenName)

    IconButton(onClick = { onClick("Icon") }) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = getColorForIcon(screenName),
            modifier = Modifier.size(35.dp)
        )
    }
}

private fun getTopBarIconForScreen(screenName: String): ImageVector {
    return when (screenName) {
        "Top Hits Anime", "My List", "Download", "New Episode Releases" -> Icons.Default.Search
        "Sort & Filter" -> Icons.Default.Add
        else -> Icons.Default.MoreVert
    }
}

private fun getColorForIcon(screenName: String): Color {
    return when (screenName) {
        "Sort & Filter", "ReviewSummary" -> Color.White
        else -> Color.Black
    }
}
