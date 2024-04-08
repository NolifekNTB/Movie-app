package com.example.movieapp.features.Home.ui.HomeScreens.TopHitsAnime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.shared.SharedViewModel
import com.example.movieapp.shared.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TopHitsAnime(mainViewModel: MainViewModel, sharedViewModel: SharedViewModel, onClick: (String) -> Unit) {
    val animeList by mainViewModel.getListTopHits().collectAsState(emptyList())
    val searchResults = remember { mutableStateOf<List<AnimeItemTopHits>>(emptyList()) }
    var searchBarVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar("Top Hits Anime"){what ->
            onClick(what)
            if(what == "Icon") searchBarVisible = !searchBarVisible
        }
        if(searchBarVisible){
            searchTopHitsBar(searchResults, mainViewModel)
        }
        if(animeList.isNotEmpty() && !searchBarVisible) {
            TopHitsAnimeList(animeList, sharedViewModel)
        } else if(searchResults.value.isNotEmpty() && searchBarVisible){
            TopHitsAnimeList(searchResults.value, sharedViewModel)
        }
    }
}

@Composable
fun TopHitsAnimeList(animeList: List<AnimeItemTopHits>, sharedViewModel: SharedViewModel) {
    LazyColumn(){
        items(animeList.size){ item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TopHitsAnimeListImage(animeList[item], Modifier.weight(0.5f))
                TopHitsAnimeListDetails(animeList[item], Modifier.weight(1f), sharedViewModel)
            }
        }
    }
}

@Composable
fun searchTopHitsBar(searchResults: MutableState<List<AnimeItemTopHits>>, viewModel: MainViewModel){
    val text = remember { mutableStateOf("")}
    val isFocused = remember { mutableStateOf(false)}

    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it;
            isFocused.value = true;
            CoroutineScope(Dispatchers.Main).launch {
                searchResults.value = viewModel.searchAllAnime(text.value)}
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, start = 10.dp, bottom = 5.dp),
        shape = RoundedCornerShape(10.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = if (isFocused.value) Color(0x339A9498)
                else Color.Gray
            )
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0x5598FB98), // Updated for focused border
            unfocusedBorderColor = Color(0x339A9498), // Updated for unfocused border
            backgroundColor = if (isFocused.value) Color(0x5598FB98) else Color(0x339A9498)
        )
    )
}




















