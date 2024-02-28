package com.example.movieapp.Home.ui.Search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.R
import com.example.movieapp.Home.data.AnimeItem
import com.example.movieapp.Home.ui.HomeMenu.ListEpisodeReleases
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@HiltViewModelMap
@Composable
fun Search(navController: NavController ,viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchBar(navController, viewModel)
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SearchBar(navController: NavController, viewModel: MainViewModel){
    var text by remember { mutableStateOf("")}
    var isFocused by remember { mutableStateOf(false)}
    var searchResults by remember { mutableStateOf<List<AnimeItem>>(emptyList()) }
    val list by viewModel.filtersList.collectAsState(emptyList())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 10.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it;
                isFocused = true;
                    CoroutineScope(Dispatchers.Main).launch {
                    searchResults = viewModel.searchAllAnime(text)}
                            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 20.dp),
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = if (isFocused) Color(0x339A9498)
                    else Color.Gray
                )
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0x5598FB98), // Updated for focused border
                unfocusedBorderColor = Color(0x339A9498), // Updated for unfocused border
                backgroundColor = if (isFocused) Color(0x5598FB98) else Color(0x339A9498)
            )
        )
        SortFilterButton(navController)
    }


    Log.d("testowanie123", list.toString())
    //Display filters
    if(list.isNotEmpty()){
        LazyRow() {
            items(list.size) { item ->
                filterList(list[item])
            }
        }
    }

    //Logic between 3 screens
    if(searchResults.isNotEmpty()){
        ListEpisodeReleases(searchResults)
    } else if (searchResults.isEmpty() && isFocused){
        NotFound("Not found", "Sorry, the keyword you entered cannot be found. Try check it again or search with other keywords.")
    } else {
        TopSearches()
    }
}


@Composable
fun filterList(name: String) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.padding(3 .dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green
        ),
        border = BorderStroke(2.dp, Color.Green)
    ) {
        Text(
            text = name,
            color = Color.White
        )
    }
}

@Composable
fun SortFilterButton(navController: NavController) {
    Card(
        modifier = Modifier.size(55.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF98FB98),
            contentColor = Color.White
        )
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Icon(
                painter = painterResource(id = R.drawable.slider),
                contentDescription = null,
                tint = Color(0xFFDDFCC8),
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate("sortFilter")
                    }
            )
        }
    }
}

@Composable
fun TopSearches() {
    Column(
        modifier = Modifier.height(70.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "Top Searches",
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold)
    }
    LazyColumn(){
        items(5)
        {
            TopSearchesItem()
        }
    }
}

@Composable
fun TopSearchesItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(end = 20.dp, start = 20.dp, top = 10.dp)
    ){
        Card(
            modifier = Modifier.size(150.dp, 125.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.attackontitan),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(15.dp))
        Text(
            text = "Attack on titan Final Season part 2",
            modifier = Modifier
                .width(160.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NotFound(title: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp))
        Text(
            text = title,
            color = Color.Green,
            fontSize = 25.sp,
            fontWeight = FontWeight.W500
        )
        Text(
            text = text,
            modifier = Modifier
                .width(350.dp)
                .padding(15.dp),
            fontSize = 15.sp,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )

    }
}












