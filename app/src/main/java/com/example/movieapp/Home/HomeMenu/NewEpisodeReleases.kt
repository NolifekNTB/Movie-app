package com.example.movieapp.Home.HomeMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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

@Preview
@Composable
fun NewEpisodeReleasesPreview() {
    NewEpisodeReleases(rememberNavController())
}

@Composable
fun NewEpisodeReleases(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        TopBar(navController, "New Episode Releases")
        ListReleases()
    }

}

@Composable
fun ListReleases() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(10){
            Box(){
                Card(){
                    Image(painter = painterResource(id = R.drawable.attackontitan),
                        contentDescription = "")
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
            }
        }
    }
}