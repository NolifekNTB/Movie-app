package com.example.movieapp.features.Download

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.features.Download.composables.DownloadListItem
import com.example.movieapp.features.Download.scaffolds.DownloadDeleteScaffold
import com.example.movieapp.shared.TopBar
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Download(onClick: () -> Unit) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipHiddenState = false,
            skipPartiallyExpanded = false)
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            DownloadDeleteScaffold(scaffoldState, scope)
        },
        sheetPeekHeight = 0.dp,
        sheetContainerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (scaffoldState.bottomSheetState.isVisible) Color.White
                    else Color.Transparent
                )
                .alpha(if (scaffoldState.bottomSheetState.isVisible) 0.5f else 1f)
        ){
            TopBar(name = "Download") { onClick() }
//        NotFound(
//            title = "Your download is empty",
//            text = "Lks like you haven't downloaded anime at all."
//        )
            DownloadList(scaffoldState, scope)
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadList(scaffoldState: BottomSheetScaffoldState, scope: CoroutineScope) {
    LazyColumn(Modifier.background(Color.White)){
        items(5){
            DownloadListItem(scaffoldState, scope, true)
        }
    }
}











