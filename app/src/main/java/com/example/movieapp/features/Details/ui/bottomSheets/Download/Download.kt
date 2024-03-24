package com.example.movieapp.features.Details.ui.bottomSheets.Download

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.features.Details.domain.DetailsViewModel
import com.example.movieapp.features.Details.ui.bottomSheets.scaffoldSheetButtons
import com.example.movieapp.features.Details.ui.bottomSheets.shareTitle
import com.example.movieapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun displayDownloadBox(scaffoldState: BottomSheetScaffoldState) {
    val viewModel = DetailsViewModel()

    Column(
        modifier = Modifier
            .height(400.dp)
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shareTitle(title = "Download", color=  Color.Black)
        Divider(thickness = 1.dp, color = Color.LightGray)
        episodesRow()
        LazyRow(){
            items(5){ item ->
                imageItem(item = item)
            }
        }
        Divider(
            thickness = 0.5.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(0.dp, 10.dp)
        )
        scaffoldSheetButtons(scaffoldState, viewModel, "download")
    }
}

@Composable
fun episodesRow(){
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Episodes",
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20   .sp
        )
    }
}

@Composable
fun imageItem(item: Int) {
    val selectedEpisodes = remember {
        mutableStateListOf<Int>()
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(150.dp, 125.dp)
            .padding(5.dp)
    ){
        Box(){
            Box(
                modifier = Modifier
                    .background(if (selectedEpisodes.contains(item)) Color.Black else Color.Transparent)
                    .alpha(if (selectedEpisodes.contains(item)) 0.3f else 1f)){
                Image(
                    painter = painterResource(id = R.drawable.home_attackontitan),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.clickable
                    {
                        if(selectedEpisodes.contains(item)) selectedEpisodes.remove(item)
                        else selectedEpisodes.add(item)
                    }
                )
                Text(
                    text = "Episode $item",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                )
            }
            if(selectedEpisodes.contains(item)){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(50.dp)
                )
            }
        }
    }
}