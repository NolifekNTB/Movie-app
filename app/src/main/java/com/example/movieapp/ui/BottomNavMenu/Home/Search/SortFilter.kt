package com.example.movieapp.ui.BottomNavMenu.Home.Search

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
import com.example.movieapp.ui.BottomNavMenu.Home.TopBar

@Preview
@Composable
fun SortFilterPreview() {
    SortFilter(rememberNavController())
}

@Composable
fun SortFilter(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController, "Sort & Filter")
        FilterList()
    }
}

@Composable
fun FilterList() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp)
    ){
        Section(title = "Sort", elements = arrayOf("Popularity", "Latest Release"), numCells = 2)
        Section(title = "Categories", elements = arrayOf("Episode", "Movie"), numCells = 2)
        Section(title = "Region", elements = arrayOf("All", "Japan", "Korea", "China"), numCells = 4)

        SectionWithSeeAllButton(title = "Genre", elements = arrayOf("All", "Action", "Slice of Life", "Magic", "Sci-Fi", "Mystery", "Comedy", "Romance", "Drama"), numCells = 3)
        SectionWithSeeAllButton(title = "Release Year", elements = arrayOf("All", "2022", "2021"), numCells = 3)

        Spacer(Modifier.height(10.dp))
        ApplyResetButtons()
    }
}

@Composable
fun Section(title: String, elements: Array<String>, numCells: Int) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
    )
    Elements(numCells, elements)
}

@Composable
fun SectionWithSeeAllButton(title: String, elements: Array<String>, numCells: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
        )
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "See all", color = Color.Green)
        }
    }
    Elements(numCells, elements)
}

@Composable
fun ApplyResetButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ApplyResetButton(text = "Reset", containerColor = Color.Green.copy(alpha = 0.2f), contentColor = Color.Green)
        ApplyResetButton(text = "Apply", containerColor = Color.Green, contentColor = Color.White)
    }
}

@Composable
fun ApplyResetButton(text: String, containerColor: Color, contentColor: Color) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(2.dp, Color.Green)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(135.dp)
                .height(40.dp)
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun Elements(howManyCells: Int, names: Array<String>){
    val selected = remember { mutableStateListOf<Boolean>() }
    repeat(howManyCells) {
        selected.add(false)
    }
    Row() {
        repeat(howManyCells){ item->
            OutlinedButton(
                onClick = { selected[item] = !selected[item] },
                modifier = Modifier.padding(3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selected[item]) Color.Green
                        else Color.White
                ),
                border = BorderStroke(2.dp, Color.Green)
            ) {
                Text(
                    text = names[item],
                    color =
                        if (selected[item]) Color.White
                        else Color.Green
                )
            }
        }
    }
}


































