package com.example.movieapp.ui.BottomNavMenu.Home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

// Constants
private val MainPhotoHeight = 300.dp
private const val AlphaValue = 0.85f
private val DetailsPadding = 15.dp
private val ButtonWidth = 100.dp
private val ButtonHeight = 35.dp

@Preview
@Composable
fun HomePreview(){
    HomeScreen(rememberNavController())
}

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        ImageHome(navController)
        RowList("Top Hits Anime", navController)
        RowList("New Episode Releases", navController)
    }
}

@Composable
fun ImageHome(navController: NavController) {
    Box {
        MainPhoto()
        ImageDetails(navController)
    }
}

@Composable
fun MainPhoto(){
    Image(
        painter = painterResource(id = R.drawable.demon_slayer),
        contentDescription = "mainPhoto",
        modifier = Modifier.height(MainPhotoHeight),
        contentScale = ContentScale.Crop,
        alpha = AlphaValue
    )
}

@Composable
fun ImageDetails(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        navController.navigate("Search")
                    },
                tint = Color.White
            )
            Spacer(Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Search",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        navController.navigate("Notification")
                    },
                tint = Color.White
            )
        }
    }
    Column(
        modifier = Modifier
            .height(MainPhotoHeight)
            .fillMaxWidth()
            .padding(DetailsPadding),
        verticalArrangement = Arrangement.Bottom
    ) {
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
            overflow = TextOverflow.Ellipsis)
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            FilledTonalButton(
                onClick = { /*TODO*/ },
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
            Spacer(
                modifier = Modifier.width(10.dp))
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonWidth, ButtonHeight),
                border = BorderStroke(2.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
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
    }
}

//////////////////////////////////////////////////////////////////////////////

@Composable
fun RowList(name: String, navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Left
        )
        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "See all",
                modifier = Modifier
                    .padding(end = 10.dp, top = 10.dp)
                    .clickable {
                        navController.navigate(name)
                    },
                fontSize = 15.sp,
                color = Color.Green)
        }
    }
    LazyRow(){
        items(3){ item ->
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(10.dp)
            ){
                //Modifier.align -> to position image index item to the left botto
                imageCard(item, Modifier.align(Alignment.BottomStart))
            }
        }
    }
}

@Composable
fun imageCard(item: Int, modifier: Modifier){
    Card(){
        Image(
            painter = painterResource(id = R.drawable.attackontitan),
            contentDescription = "mainPhoto",
            alpha = AlphaValue
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
    ){
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "9.8",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold)
        }
    }
    Text(
        text = "$item",
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp),
        textAlign = TextAlign.Left,
        fontSize = 33.sp,
        color = Color.White)
}
























