package com.example.movieapp.Home.ui.HomeScreens.Search.sortFilter

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.Home.logic.viewModel.MainViewModel

@Composable
fun Section(title: String, elements: Array<String>, numCells: Int, viewModel: MainViewModel) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
    )
    SectionsElement(numCells, elements, viewModel)
}

@Composable
fun SectionWithSeeAllButton(title: String, elements: Array<String>, numCells: Int, viewModel: MainViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = { }) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 15.dp, start = 8.dp, bottom = 15.dp)
            )
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "See all", color = Color.Green)
        }
    }
    SectionsElement(numCells, elements, viewModel)
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SectionsElement(howManyCells: Int, names: Array<String>, viewModel: MainViewModel){
    val selected = remember { mutableStateListOf<Boolean>() }
    val selectedList by viewModel.filtersList.collectAsState(emptyList())

    LaunchedEffect(selectedList){
        for(i in names.indices){
            if(selectedList.contains(names[i])){
                selected.add(true)
            } else {
                selected.add(false)
            }
        }
    }

    if(selected.size > 0){
        Row() {
            repeat(howManyCells){ item->
                OutlinedButton(
                    onClick = {selected[item] = !selected[item];
                        if(selected[item]) viewModel.updateList(names[item])
                        else viewModel.removeFromList(names[item])
                    },
                    modifier = Modifier.padding(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selected[item]) Color.Green
                        else Color.White,
                    ),
                    border = BorderStroke(2.dp, Color.Green)
                ) {
                    Text(
                        text = names[item],
                        color = if (selected[item]) Color.White
                        else Color.Green
                    )
                }
            }
        }
    }
}

@Composable
fun ApplyResetButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ApplyResetButtonsItem(text = "Reset", containerColor = Color.Green.copy(alpha = 0.2f), contentColor = Color.Green)
        ApplyResetButtonsItem(text = "Apply", containerColor = Color.Green, contentColor = Color.White)
    }
}

@Composable
fun ApplyResetButtonsItem(text: String, containerColor: Color, contentColor: Color) {
    OutlinedButton(
        onClick = { },
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











