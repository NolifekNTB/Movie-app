package com.example.movieapp.features.Home.ui.Search.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.ui.Search.domain.SearchViewModel
import com.example.movieapp.features.Home.ui.composables.ListEpisodeReleases
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@HiltViewModelMap
@SuppressLint("SuspiciousIndentation")
@Composable
fun Search(viewModel: SearchViewModel, onClick: () -> Unit) {
    val text = remember { mutableStateOf("")}
    val isFocused = remember { mutableStateOf(false)}
    val searchResults = remember { mutableStateOf<List<AnimeItemTopHits>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp, start = 10.dp, top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            searchBar(text, isFocused, viewModel, searchResults, Modifier.weight(1f))
            filterButton(onClick, viewModel)
        }
        displayChoseFilters(viewModel = viewModel)
        logicBetweenSearchScreens(searchResults, isFocused)
    }
}

@Composable
fun searchBar(
    text: MutableState<String>,
    isFocused: MutableState<Boolean>,
    viewModel: SearchViewModel,
    searchResults: MutableState<List<AnimeItemTopHits>>,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it;
            isFocused.value = true;
            CoroutineScope(Dispatchers.Main).launch {
                searchResults.value = viewModel.searchAllAnime(text.value)}
        },
        modifier = modifier
            .padding(end = 20.dp),
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

@Composable
fun filterButton(onClick: () -> Unit, viewModel: SearchViewModel) {
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
                painter = painterResource(id = R.drawable.search_slider),
                contentDescription = null,
                tint = Color(0xFFDDFCC8),
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onClick();
                        viewModel.applyFilters(false)
                    }
            )
        }
    }
}

@Composable
fun displayChoseFilters(viewModel: SearchViewModel) {
    val list = viewModel.filtersList.collectAsState(emptyList()).value
    val isApply = viewModel.applyFilters.collectAsState().value

    if (isApply) {
        if (list.isNotEmpty()) {
            LazyRow() {
                items(list.size) { item ->
                    choseFilter(list[item])
                }
            }
        }
    }
}

@Composable
fun logicBetweenSearchScreens(searchResults: MutableState<List<AnimeItemTopHits>>, isFocused: MutableState<Boolean>) {
    if (searchResults.value.isNotEmpty()) {
        ListEpisodeReleases(searchResults.value)
    } else if (searchResults.value.isEmpty() && isFocused.value) {
        NotFound(
            "Not found",
            "Sorry, the keyword you entered cannot be found. Try check it again or search with other keywords."
        )
    } else {
        SearchScreenDefault()
    }
}











