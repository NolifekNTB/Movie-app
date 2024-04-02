package com.example.movieapp.features.Details

import  android.util.Log
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
import androidx.compose.runtime.collectAsState
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
import coil.compose.AsyncImage
import com.example.movieapp.features.Details.bottomSheets.Download.displayDownloadBox
import com.example.movieapp.features.Details.bottomSheets.Rating.giveRatingBox
import com.example.movieapp.features.Details.bottomSheets.shareDisplayBox
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Details.composables.Comments
import com.example.movieapp.features.Details.composables.DescriptionSection
import com.example.movieapp.features.Details.composables.MoreLikeThis
import com.example.movieapp.features.Details.composables.belowTitleSection
import com.example.movieapp.features.Details.composables.buttonsSection
import com.example.movieapp.features.Details.composables.titleSection
import com.example.movieapp.shared.SharedViewModel
import org.jetbrains.annotations.Async

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: SharedViewModel, id: Int, onClick: (String) -> Unit) {
    val animeList = viewModel.getTopHits().collectAsState(emptyList()).value

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
                    displayDownloadBox(scaffoldState, viewModel)
                }
                "rating" -> {
                    giveRatingBox(scaffoldState, viewModel)
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
                if(animeList.isNotEmpty()){
                     MainPhoto(animeList[id-1]){ popUpOrNextScreen ->
                            onClick(popUpOrNextScreen)
                    }
                    Description(scaffoldState, whichState, animeList[id-1]){what ->
                        onClick(what)
                    }
                    MoreLikeThisComments(animeList, animeList[id-1]){ popUpOrNextScreen ->
                        onClick(popUpOrNextScreen)
                    }
                }
            }
    }
}


@Composable
fun MainPhoto(animeItem: AnimeItemTopHits, onClick: (String) -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)) {
            AsyncImage(
                model = animeItem.image,
                contentDescription = "animePhoto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
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
    animeItem: AnimeItemTopHits,
    onClick: (String) -> Unit
    ) {
    val scope = rememberCoroutineScope()

    Column(Modifier.background(Color.White)) {
        titleSection(scaffoldState, whichState, scope, animeItem.name)
        belowTitleSection(scaffoldState, whichState, scope, animeItem)
        buttonsSection(scaffoldState, whichState, scope, animeItem){what, url ->
            onClick(what)
        }
        DescriptionSection(animeItem)
    }
}

@Composable
fun MoreLikeThisComments(animeList: List<AnimeItemTopHits>, animeItem: AnimeItemTopHits, onClick: (String) -> Unit) {
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

        val filteredList = filterAnimeList(animeList, animeItem)
        when (selectedSection) {
            0 -> {
                MoreLikeThis(filteredList)
            }
            1 -> {
                 Comments(){ popUpOrNextScreen ->
                     onClick(popUpOrNextScreen)
                 }
            }
        }
    }
}


fun filterAnimeList(animeList: List<AnimeItemTopHits>, animeItem: AnimeItemTopHits): List<AnimeItemTopHits> {
    return animeList.filter { it.id != animeItem.id && it.genres.any { genre -> animeItem.genres.contains(genre) } }
}














