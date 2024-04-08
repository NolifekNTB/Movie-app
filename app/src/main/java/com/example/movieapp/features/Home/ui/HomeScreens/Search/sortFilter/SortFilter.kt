package com.example.movieapp.features.Home.ui.HomeScreens.Search.sortFilter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.features.Home.domain.SearchViewModel
import com.example.movieapp.shared.TopBar

@Composable
fun SortFilter(onClick: () -> Unit, viewModel: SearchViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar("Sort & Filter"){_ -> onClick()}
        MainContent(viewModel){onClick()}
    }
}

@Composable
fun MainContent(viewModel: SearchViewModel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp)
    ){
        Section(
            title = "Sort",
            elements = arrayOf("Popularity", "Latest Release"),
            numCells = 2,
            viewModel = viewModel)
        Section(
            title = "Categories",
            elements = arrayOf("Episode", "Movie"),
            numCells = 2 ,
            viewModel = viewModel)
        Section(
            title = "Region",
            elements = arrayOf("Japan", "Korea", "China"),
            numCells = 3 ,
            viewModel = viewModel)

        SectionWithSeeAllButton(
            title = "Genre",
            elements = arrayOf("Action", "Slice of Life", "Magic", "Sci-Fi", "Mystery",
            "Comedy", "Romance", "Drama"),
            numCells = 3,
            viewModel = viewModel)
        SectionWithSeeAllButton(
            title = "Release Year",
            elements = arrayOf("2022", "2021"),
            numCells = 2,
            viewModel = viewModel)

        Spacer(modifier = Modifier.height(10.dp))
        ApplyResetButtons(viewModel = viewModel){onClick()}
    }
}
























