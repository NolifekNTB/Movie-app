package com.example.movieapp.Download

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.Details.ui.BottomSheetScaffolds.Download.downloadBox
import com.example.movieapp.Details.ui.BottomSheetScaffolds.shareTitle
import com.example.movieapp.Details.ui.buttonsSection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadDeleteScaffold(scaffoldState: BottomSheetScaffoldState, scope: CoroutineScope) {
    Column(
        modifier = Modifier
            .height(425.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shareTitle(title = "Delete", color = Color.Red)
        Divider(thickness = 1.dp, color = Color.LightGray)
        DownloadDescription()
        DownloadDeleteContent(scaffoldState, scope)
        Divider(thickness = 1.dp, color = Color.LightGray)
        DownloadDeleteButtons(scaffoldState, scope)
    }
}

@Composable
fun DownloadDescription(){
    Text(
        text = "Are you sure you want to delete this download?",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadDeleteContent(scaffoldState: BottomSheetScaffoldState, scope: CoroutineScope) {
    DownloadListItem(
        scaffoldState = scaffoldState,
        scope = scope,
        shouldShow = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadDeleteButtons(scaffoldState: BottomSheetScaffoldState, scope: CoroutineScope){
    Row(modifier = Modifier.padding(top = 10.dp)) {
        FilledTonalButton(
            onClick = {
                scope.launch {
                    scaffoldState.bottomSheetState.hide()
                }
            },
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green.copy(alpha = 0.15f),
                contentColor = Color.Green
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Text(
                text = "Cancel",
                modifier = Modifier
                    .padding(start = 3.dp),
                fontSize = 15.sp,
                color = Color.Green
            )
        }
        Spacer(modifier = Modifier.width(10.dp))

        FilledTonalButton(
            onClick = {
                scope.launch { scaffoldState.bottomSheetState.hide() };
            },
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Text(
                text = "Yes, delete",
                modifier = Modifier
                    .padding(start = 3.dp),
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}

