package com.example.movieapp.features.Details.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.movieapp.features.Details.ui.bottomSheets.Download.displayDownloadBox
import com.example.movieapp.features.Details.ui.bottomSheets.Rating.giveRatingBox
import com.example.movieapp.features.Details.ui.bottomSheets.shareDisplayBox
import com.example.movieapp.R
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Details.data.BottomSheetContent
import com.example.movieapp.features.Details.data.Section
import com.example.movieapp.features.Details.domain.DetailViewModel
import com.example.movieapp.shared.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: SharedViewModel, id: Int, onClick: (String) -> Unit) {
    val detailVM = DetailViewModel()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipHiddenState = false,
            skipPartiallyExpanded = false
        )
    )
    val bottomSheetContent = remember { mutableStateOf(BottomSheetContent.NONE) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = { BottomSheetContentDisplay(bottomSheetContent.value, scaffoldState, viewModel) },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = Color.White
    ) {
        DetailScreenContent(viewModel, detailVM, scaffoldState, bottomSheetContent, id) { direction ->
            onClick(direction)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContentDisplay(
    content: BottomSheetContent,
    scaffoldState: BottomSheetScaffoldState,
    viewModel: SharedViewModel
) {
    when (content) {
        BottomSheetContent.SHARE -> shareDisplayBox()
        BottomSheetContent.DOWNLOAD -> displayDownloadBox(scaffoldState, viewModel)
        BottomSheetContent.RATING -> giveRatingBox(scaffoldState, viewModel)
        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    viewModel: SharedViewModel,
    detailVM: DetailViewModel,
    scaffoldState: BottomSheetScaffoldState,
    bottomSheetContent: MutableState<BottomSheetContent>,
    id: Int,
    onNavigate: (String) -> Unit,
) {
    val animeList = viewModel.getTopHits().collectAsState(emptyList()).value

    LaunchedEffect(bottomSheetContent.value) {
        if (bottomSheetContent.value != BottomSheetContent.NONE) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.hide()
        }
    }

    if (animeList.isNotEmpty()){
        val animeItem = animeList[id-1]

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(if (scaffoldState.bottomSheetState.isVisible) Color.Black else Color.Transparent)
                .alpha(if (scaffoldState.bottomSheetState.isVisible) 0.5f else 1f)
        )  {
            MainBanner(animeItem) { direction -> onNavigate(direction) }
            Description(
                animeItem,
                onActionTriggered = { content -> bottomSheetContent.value = content },
                onNavigate = { direction -> onNavigate(direction)  }
            )
            MoreLikeThisComments(animeList, animeItem, detailVM) { direction -> onNavigate(direction)  }
        }
    }
}

@Composable
fun MainBanner(animeItem: AnimeItemTopHits, onClick: (String) -> Unit) {
    Box {
        MainBannerImage(animeItem.image)
        MainBannerOthers(){direction -> onClick(direction)}
    }
}

@Composable
fun MainBannerImage(image: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "animePhoto",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MainBannerOthers(onClick: (String) -> Unit) {
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
            }
        )
        Icon(
            painter = painterResource(R.drawable.detail_mirorr),
            contentDescription = "ArrowBack",
            tint = Color.White,
            modifier = Modifier.size(20.dp, 30.dp))
    }
}

@Composable
fun Description(
    animeItem: AnimeItemTopHits,
    onActionTriggered: (BottomSheetContent) -> Unit,
    onNavigate: (String) -> Unit
){
    Column(Modifier.background(Color.White)) {
        TitleSection(animeItem.name) { content -> onActionTriggered(content)}
        RatingYearGenres(animeItem) { content -> onActionTriggered(content)}
        ButtonsSection(
            onNavigate = {direction -> onNavigate(direction) },
            onActionTriggered = {content -> onActionTriggered(content)}
        )
        DescriptionSection(animeItem)
    }
}

@Composable
fun MoreLikeThisComments(
    animeList: List<AnimeItemTopHits>,
    animeItem: AnimeItemTopHits,
    detailVM: DetailViewModel,
    onNavigate: (String) -> Unit
) {
    var selectedSection by remember { mutableStateOf(Section.MoreLikeThis) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        SectionTabs(selectedSection) { selectedSection = it }

        Spacer(modifier = Modifier.height(8.dp))

        val filteredList = detailVM.filterAnimeList(animeList, animeItem)
        when (selectedSection) {
            Section.MoreLikeThis -> { MoreLikeThis(filteredList) }
            Section.Comments -> {
                Comments(){ direction ->
                    onNavigate(direction)
                }
            }
        }
    }
}

@Composable
fun SectionTabs(
    selectedSection: Section,
    onSectionSelected: (Section) -> Unit
) {
    val sections = Section.values()

    Row {
        sections.forEach { section ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .clickable { onSectionSelected(section) }
            ) {
                Text(
                    text = when(section) {
                        Section.MoreLikeThis -> "More Like This"
                        Section.Comments -> "Comments"
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = if (selectedSection == section) Color.Green else Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 4.dp)
                )
                if (selectedSection == section) {
                    Divider(color = Color.Green, thickness = 2.dp, modifier = Modifier.align(Alignment.BottomCenter))
                }
            }
        }
    }
}

