package com.example.movieapp.Download

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.Home.ui.HomeScreens.ListEpisodeReleases
import com.example.movieapp.Home.ui.HomeScreens.Search.NotFound
import com.example.movieapp.MyList.logic.ListViewModel
import com.example.movieapp.R
import com.example.movieapp.core.other.TopBar

@Composable
fun Download(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(name = "Download") { onClick() }
//        NotFound(
//            title = "Your download is empty",
//            text = "Lks like you haven't downloaded anime at all."
//        )
        DownloadList()
    }
}

@Composable
fun DownloadList(){
    LazyColumn(){
        items(5){ item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){
                Card(
                    modifier = Modifier
                        .size(150.dp, 125.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.attackontitan),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth)
                }
                Column(
                    modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                ) {
                    Text(text = "Attack on titan",
                        modifier = Modifier
                            .width(125.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(text = "Episodes 1040")
                    Spacer(Modifier.height(5.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Card(
                            modifier = Modifier
                                .size(90.dp, 30.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0x2200FF00)
                            ),
                            shape = RoundedCornerShape(corner = CornerSize(10.dp))
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ){
                                Text(text = "178.1 MB", color = Color.Green)
                            }
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.trash),
                            contentDescription = "trashIcon",
                            tint = Color.Green)
                    }
                }
            }
        }
    }
}











