package com.example.movieapp.Home.Search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.Home.HomeMenu.TopBar

@Preview
@Composable
fun SortFilterPreview() {
    SortFilter(rememberNavController())
}

@Composable
fun SortFilter(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        TopBar(navController, "Sort & Filter")
        FilterList()
    }
}


@Composable
fun FilterList() {
        Column(Modifier.verticalScroll(rememberScrollState()).padding(bottom = 20.dp)){
            Text(
                text = "Sort",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
            )
            Elements(HowManyCells = 2, arrayOf("Popularity", "Latest Release"))

            Text(
                text = "Categories",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
            )
            Elements(HowManyCells = 2, arrayOf("Episode", "Movie"))

            Text(
                text = "Region",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
            )
            Elements(HowManyCells = 4, arrayOf("All", "Japan", "Korea", "China"))

            //Genre
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Genre",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "See all", color = Color.Green)
                }
            }
            Elements(HowManyCells = 3, arrayOf("All", "Action", "Slice of Life"))
            Elements(HowManyCells = 3, arrayOf("Magic", "Sci-Fi", "Mystery"))
            Elements(HowManyCells = 3, arrayOf("Comedy", "Romance", "Drama"))

            //Release year
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Release Year",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "See all", color = Color.Green)
                }
            }
            Elements(HowManyCells = 3, arrayOf("All", "2022", "2021"))

            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green.copy(
                            alpha = 0.2f
                        ),
                        contentColor = Color.Green
                    ),
                    border = BorderStroke(2.dp, Color.Green)
                ) {
                    Text(
                        text = "Reset",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(135.dp)
                            .height(40.dp)
                            .padding(top = 10.dp)
                    )
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, Color.Green)
                ) {
                    Text(
                        text = "Apply",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(135.dp)
                            .height(40.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
    }
}

@Composable
fun Elements(HowManyCells: Int, names: Array<String>){
    val selected = remember { mutableStateListOf<Boolean>() }
    repeat(HowManyCells) {
        selected.add(false)
    }
        Row() {
            repeat(HowManyCells){item->
            OutlinedButton(
                onClick = { selected[item] = !selected[item] },
                modifier = Modifier.padding(3.dp),
                colors = ButtonDefaults.buttonColors(
                    if (selected[item]) Color.Green else Color.White
                ),
                border = BorderStroke(2.dp, Color.Green)
            ) {
                Text(names[item], color = if (selected[item]) Color.White else Color.Green)
            }
        }
    }
}


































