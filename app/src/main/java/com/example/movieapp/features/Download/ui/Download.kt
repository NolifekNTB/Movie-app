package com.example.movieapp.features.Download.ui

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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.features.Download.ui.scaffolds.DeleteScaffold
import com.example.movieapp.shared.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            DeleteScaffold(scaffoldState, scope)
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
            TopBar(title = "Download") { onClick() }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadListItem(
    scaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    shouldShow:Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        DownloadListItemImage()
        DownloadListItemDescription(scaffoldState, scope, shouldShow)
    }
}

@Composable
fun DownloadListItemImage() {
    Card(
        modifier = Modifier
            .size(150.dp, 125.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.home_attackontitan),
            contentDescription = null,
            contentScale = ContentScale.FillWidth)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadListItemDescription(
    scaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    shouldShow:Boolean
) {
    Column(
        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
    ) {
        Text(text = "Attack on titan",
            modifier = Modifier
                .width(125.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(5.dp))
        Text(text = "Episodes 1040")
        Spacer(Modifier.height(5.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            MegaBytesBox()
            TrashIcon(shouldShow, scope, scaffoldState)
        }
    }
}

@Composable
fun MegaBytesBox() {
    Card(
        modifier = Modifier
            .size(90.dp, 30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x2200FF00)
        ),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Text(text = "178.1 MB", color = Color.Green)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashIcon(
    shouldShow:Boolean,
    scope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
) {
    if(shouldShow){
        Icon(
            painter = painterResource(id = R.drawable.download_trash),
            contentDescription = "trashIcon",
            tint = Color.Green,
            modifier = Modifier
                .clickable {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
        )
    }
}