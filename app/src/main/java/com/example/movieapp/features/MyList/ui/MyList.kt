package com.example.movieapp.features.MyList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.core.network.models.shared.Trailer
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.features.Home.ui.Search.NotFound
import com.example.movieapp.features.Home.ui.composables.ListEpisodeReleases
import com.example.movieapp.shared.SharedViewModel
import com.example.movieapp.shared.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MyList(viewModel: SharedViewModel, onClick: (String) -> Unit) {
    val animeList by viewModel.getListMyList().collectAsState(emptyList())
    val searchResults = remember { mutableStateOf<List<AnimeItemMyList>>(emptyList()) }
    var searchBarVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(name = "My List") { where ->
            if(where == "Icon") {
                searchBarVisible = !searchBarVisible
            } else {
                onClick("Back")
            }
        }
        if(searchBarVisible){
            searchMyListBar(searchResults = searchResults, viewModel = viewModel)
        }
        if(animeList.isEmpty()){
            NotFound(title = "Your list is empty", text = "It seems that you haven't added" +
                    "any anime to the list")
        } else if (searchBarVisible && searchResults.value.isNotEmpty()) {
            ListEpisodeReleases(animeList = fromMyListToTopHits(searchResults.value))
        } else {
            ListEpisodeReleases(animeList = fromMyListToTopHits(animeList))
        }
    }
}

fun fromMyListToTopHits(animeList: List<AnimeItemMyList>): List<AnimeItemTopHits> {
    val list = mutableListOf<AnimeItemTopHits>()
    for (i in animeList) {
        val anime = AnimeItemTopHits(
            id = i.id,
            name = i.name,
            image = i.image,
            rating = i.rating,
            year = 2024,
            genres = emptyList(),
            description = "",
            trailer = Trailer("")
        )
        list.add(anime)
    }
    return list
}

@Composable
fun searchMyListBar(searchResults: MutableState<List<AnimeItemMyList>>, viewModel: SharedViewModel){
    val text = remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }

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