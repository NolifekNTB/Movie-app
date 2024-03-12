package com.example.movieapp.core.Details.ui.BottomSheetScaffolds

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.core.Details.logic.DetailsViewModel
import com.example.movieapp.core.Details.ui.BottomSheetScaffolds.Download.downloadBox
import kotlinx.coroutines.launch

@Composable
fun shareTitle(title: String, color: Color) {
    Text(
        text = title,
        fontSize = 20.sp,
        color = color,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(15.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldSheetButtons(
    scaffoldState: BottomSheetScaffoldState,
    viewModel: DetailsViewModel,
    type: String
) {
    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }

    val startDownloadPhoto = remember {
        mutableStateOf(false)
    }

    Row {
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
                if(type == "download"){
                    showDialog.value = true;
                    startDownloadPhoto.value = true
                }
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
                text = if(type == "Download") "Download" else "Submit",
                modifier = Modifier
                    .padding(start = 3.dp),
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
    if(showDialog.value){
        downloadBox(showDialog, startDownloadPhoto, viewModel)
    }
}
   