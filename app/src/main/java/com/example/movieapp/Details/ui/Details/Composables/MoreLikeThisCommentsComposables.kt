package com.example.movieapp.Details.ui.Details.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.R

@Composable
fun MoreLikeThis() {
    LazyRow() {
        items(3){
            Card(
                modifier = Modifier
                    .size(150.dp, 250.dp)
                    .padding(5.dp)
            ){
                Box(){
                    Image(
                        painter = painterResource(R.drawable.attackontitan),
                        contentDescription = "mainPhoto",
                        contentScale = ContentScale.FillWidth
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp)
                            .size(30.dp, 20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(5.dp))
                    ){
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "8.8",
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
}

@Composable
fun Comments(navController: NavController) {
    //Follow comment
    var selected by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "29.5K Comments",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        TextButton(onClick = { navController.navigate("comments") }) {
            Text(
                text = "See all",
                color = Color.Green
            )
        }
    }

    Column(){
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(
                    modifier = Modifier
                        .size(50.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Jan Kowalski",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = ""
            )
        }
        Row(){
            Text(text = "\n" +
                    "\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut " +
                    "efficitur dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
        }
        Row(
            Modifier
                .width(225.dp)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = { selected = !selected }) {
                Icon(
                    imageVector = if (selected) Icons.Outlined.Favorite
                    else Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = if(selected) Color.Black else Color.Green
                )
            }
            Text(text = "125")
            Spacer(modifier = Modifier.width(25.dp))
            Text(text = "4 days ago")
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Reply")
            }
        }
    }
}