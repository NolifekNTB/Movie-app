package com.example.movieapp.core.Details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.core.Details.ui.BottomSheetScaffolds.Download.displayDownloadBox
import com.example.movieapp.core.Details.ui.BottomSheetScaffolds.Rating.giveRatingBox
import com.example.movieapp.core.Details.ui.BottomSheetScaffolds.shareDisplayBox
import com.example.movieapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(onClick: (String) -> Unit) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipHiddenState = false,
            skipPartiallyExpanded = false)
    )
    val whichState = remember {
        mutableStateOf("")
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            when (whichState.value) {
                "share" -> {
                    shareDisplayBox()
                }
                "download" -> {
                    displayDownloadBox(scaffoldState)
                }
                "rating" -> {
                    giveRatingBox(scaffoldState)
                }
            }
        },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = Color.White
    ) {
        Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(
                        if (scaffoldState.bottomSheetState.isVisible) Color.Black
                        else Color.Transparent
                    )
                    .alpha(if (scaffoldState.bottomSheetState.isVisible) 0.5f else 1f)
            ) {
                MainPhoto(){ popUpOrNextScreen ->
                    onClick(popUpOrNextScreen)
                }
                Description(scaffoldState, whichState, onClick)
                Episodes()
                MoreLikeThisComments(){ popUpOrNextScreen ->
                    onClick(popUpOrNextScreen)
                }
            }
    }
}


@Composable
fun MainPhoto(onClick: (String) -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)) {
            Image(
                painter = painterResource(R.drawable.home_demonslayer),
                contentDescription = "MainPhoto",
                contentScale = ContentScale.FillHeight)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp)
        ){
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "ArrowBack",
                tint = Color.White,
                modifier = Modifier.clickable{
                    onClick("popUp")
                })
            Icon(
                painter = painterResource(R.drawable.detail_mirorr),
                contentDescription = "ArrowBack",
                tint = Color.White,
                modifier = Modifier.size(20.dp, 30.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Description(
    scaffoldState: BottomSheetScaffoldState,
    whichState: MutableState<String>,
    onClick: (String) -> Unit
    ) {
    val scope = rememberCoroutineScope()

    Column(Modifier.background(Color.White)) {
        titleSection(scaffoldState, whichState, scope)
        belowTitleSection(scaffoldState, whichState, scope)
        buttonsSection(scaffoldState, whichState, scope, onClick)
        descriptionSection()
    }
}

@Composable
fun Episodes() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Season 1") }
    Column(
        Modifier
            .padding(20.dp)
            .background(Color.White)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Episodes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            // Dropdown menu
            Box(){
                TextButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.TopEnd)
                ) {
                    Text(selectedText, color = Color.Green)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color.Green
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        selectedText = "Season 1"
                        expanded = false
                    }) {
                        Text("Season 1")
                    }
                    DropdownMenuItem(onClick = {
                        selectedText = "Season 2"
                        expanded = false
                    }) {
                        Text("Season 2")
                    }
                    DropdownMenuItem(onClick = {
                        selectedText = "Season 3"
                        expanded = false
                    }) {
                        Text("Season 3")
                    }
                }
            }
        }
        LazyRow(){
            items(5){item ->
                    Box(
                        modifier = Modifier
                            .size(133.dp, 125.dp)
                            .padding(end = 10.dp)
                    ){
                        Card(){
                            Image(
                                painter = painterResource(R.drawable.home_attackontitan),
                                contentDescription = "",
                                contentScale = ContentScale.FillWidth
                            )
                        }
                        Text(
                            text = "Episode $item",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 10.dp, bottom = 10.dp)
                        )
                }
            }
        }
    }
}

@Composable
fun MoreLikeThisComments(onClick: (String) -> Unit) {
    var selectedSection by remember { mutableStateOf(0) }
    val sections = listOf("More Like This", "Comments")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row() {
            sections.forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .clickable { selectedSection = index }
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (selectedSection == index) Color.Green else Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 4.dp)
                    )
                    if (selectedSection == index) {
                        Divider(
                            color = Color.Green,
                            thickness = 2.dp,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Content of the selected section
        when (selectedSection) {
            0 -> {
                MoreLikeThis()
            }
            1 -> {
                 Comments(){ popUpOrNextScreen ->
                     onClick(popUpOrNextScreen)
                 }
            }
        }
    }
}