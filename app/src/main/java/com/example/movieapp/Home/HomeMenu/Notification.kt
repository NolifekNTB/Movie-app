package com.example.movieapp.Home.HomeMenu

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
fun NotificationPreview() {
    Notification(rememberNavController())
}

@Composable
fun Notification(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        TopBar(navController = navController, name = "Notification")
        ListNotification()
    }
}

@Composable
fun ListNotification(){
    LazyColumn(Modifier.fillMaxSize()){
        items(7){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){
                Card( modifier = Modifier
                    .size(150.dp, 125.dp)){
                    Image(painter = painterResource(id = R.drawable.attackontitan),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth)
                }
                Column(Modifier.padding(top = 10.dp, start = 10.dp)) {
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
                    Card(
                        modifier = Modifier
                            .size(60.dp, 25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(3.dp))
                    ) {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Update",
                                textAlign = TextAlign.Center,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(top = 15.dp)) {
                    Text(text = "12/20/2024",
                        fontSize = 10.sp)
                }
            }
        }
    }
}
