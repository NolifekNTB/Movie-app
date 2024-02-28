package com.example.movieapp.Home.ui.HomeMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.Home.data.AnimeItem
import com.example.movieapp.Home.ui.TopBar

@Preview
@Composable
fun NewEpisodeReleasesPreview() {
    NewEpisodeReleases(rememberNavController())
}

@Composable
fun NewEpisodeReleases(navController: NavController) {
    var exampleList = arrayListOf(
        AnimeItem(1, "attack", R.drawable.attackontitan, 9.2),
        AnimeItem(2, "Naruto", R.drawable.naruto, 9.3),
        AnimeItem(3, "Dragon Ball", R.drawable.attackontitan, 9.5),
        AnimeItem(4, "Death Note", R.drawable.naruto, 9.1),
        AnimeItem(5, "One Piece", R.drawable.attackontitan, 9.4),
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController, "New Episode Releases")
        ListEpisodeReleases(exampleList)
    }

}
@Composable
fun ListEpisodeReleases(animeList: List<AnimeItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(animeList.size){ item ->
            EpisodeItem(animeList[item])
        }
    }
}

@Composable
fun EpisodeItem(episode: AnimeItem) {
    Box(){
        Card(){
            Image(
                painter = painterResource(id = episode.image),
                contentDescription = "")
        }
        EpisodeRating()
    }
}

@Composable
fun EpisodeRating() {
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
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "9.8",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
