package com.example.movieapp.features.Details.ui.bottomSheets

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.features.Details.ui.bottomSheets.Scaffolds.DownloadBox
import com.example.movieapp.shared.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScaffoldTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(15.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSheetButtons(
    scaffoldState: BottomSheetScaffoldState,
    viewModel: SharedViewModel,
    type: String
) {
    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }
    val startDownloadPhoto = remember { mutableStateOf(false) }

    ButtonRow(scaffoldState, scope, showDialog, startDownloadPhoto, type)

    if (showDialog.value) {
        DownloadBox(showDialog, startDownloadPhoto, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ButtonRow(
    scaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope,
    showDialog: MutableState<Boolean>,
    startDownloadPhoto: MutableState<Boolean>,
    type: String
) {
    Row {
        CancelButton(scope, scaffoldState, Modifier.weight(1f))
        Spacer(modifier = Modifier.width(10.dp))
        ConfirmButton(type, scope, scaffoldState, showDialog, startDownloadPhoto, Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CancelButton(
    scope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState,
    modifier: Modifier
) {
    FilledTonalButton(
        onClick = { scope.launch { scaffoldState.bottomSheetState.hide() } },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green.copy(alpha = 0.15f),
            contentColor = Color.Green
        ),
        contentPadding = PaddingValues(5.dp)
    ) {
        Text(
            "Cancel",
            modifier = Modifier.padding(start = 3.dp),
            fontSize = 15.sp,
            color = Color.Green
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfirmButton(
    type: String,
    scope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState,
    showDialog: MutableState<Boolean>,
    startDownloadPhoto: MutableState<Boolean>,
    modifier: Modifier
) {
    FilledTonalButton(
        onClick = {
            scope.launch { scaffoldState.bottomSheetState.hide() }
            if (type == "download") {
                showDialog.value = true
                startDownloadPhoto.value = true
            }
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(5.dp)
    ) {
        Text(
            if (type == "download") "Download" else "Submit",
            modifier = Modifier.padding(start = 3.dp),
            fontSize = 15.sp,
            color = Color.White
        )
    }
}