package com.example.movieapp.features.Details.ui.bottomSheets.Scaffolds

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.features.Details.ui.bottomSheets.ScaffoldSheetButtons
import com.example.movieapp.features.Details.ui.bottomSheets.ScaffoldTitle
import com.example.movieapp.shared.SharedViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayDownloadBox(scaffoldState: BottomSheetScaffoldState, viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .height(400.dp)
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScaffoldTitle(title = "Download")
        Divider(thickness = 1.dp, color = Color.LightGray)
        EpisodeRow()
        EpisodeImages(viewModel)
        Divider(thickness = 0.5.dp, color = Color.LightGray, modifier = Modifier.padding(0.dp, 10.dp))
        ScaffoldSheetButtons(scaffoldState, viewModel, "download")
    }
}

@Composable
fun EpisodeRow(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Episodes",
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun EpisodeImages(viewModel: SharedViewModel) {
    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(5) { index ->
            EpisodeImageItem(index)
        }
    }
}

@Composable
fun EpisodeImageItem(index: Int) {
    val selectedEpisodes = remember { mutableStateListOf<Int>() }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(150.dp, 125.dp)
            .padding(5.dp)
    ){
        Box {
            ItemMainContent(selectedEpisodes, index)
            ItemSelectedIcon(selectedEpisodes, index, Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ItemMainContent(selectedEpisodes: MutableList<Int>, index: Int) {
    Box(
        modifier = Modifier
            .background(if (selectedEpisodes.contains(index)) Color.Black else Color.Transparent)
            .alpha(if (selectedEpisodes.contains(index)) 0.3f else 1f)){
        Image(
            painter = painterResource(id = R.drawable.home_attackontitan),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.clickable
            {
                if(selectedEpisodes.contains(index)) selectedEpisodes.remove(index)
                else selectedEpisodes.add(index)
            }
        )
        Text(
            text = "Episode $index",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        )
    }
}

@Composable
fun ItemSelectedIcon(selectedEpisodes: MutableList<Int>, index: Int, modifier: Modifier) {
    if(selectedEpisodes.contains(index)){
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = Color.Green,
            modifier = modifier
                .size(50.dp)
        )
    }
}

@Composable
fun DownloadBox(
    showDialog: MutableState<Boolean>,
    startDownloadPhoto: MutableState<Boolean>,
    viewModel: SharedViewModel
) {
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        text = { DownloadBoxText(startDownloadPhoto, viewModel) },
        confirmButton = { DownloadBoxConfirm(showDialog) },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun DownloadBoxText(
    startDownloadPhoto: MutableState<Boolean>,
    viewModel: SharedViewModel
) {
    val context = LocalContext.current
    val downloadManager = remember { context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager }

    val downloadId = remember { mutableLongStateOf(0L) }
    val progress = remember { mutableIntStateOf(0) }

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Download",
            color = Color.Green,
            fontSize = 25.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Episode 1 is stil downloading...\n " +
                    "Please wait or hide the process",
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Divider(thickness = 1.dp, color = Color.LightGray)
        Column(
        ) {
            LinearProgressIndicator(
                progress = progress.intValue / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            if(startDownloadPhoto.value){
                downloadId.longValue = viewModel.downloadFile("https://upload.wikimedia.org/wikipedia/commons/8/80/Kwiatek.jpg", context)
                startDownloadPhoto.value = false
            }

            LaunchedEffect(downloadId.longValue){
                observeDownloadProgress(downloadId.longValue, downloadManager) { currentProgress ->
                    progress.intValue = currentProgress
                }
            }
        }
    }
}

@Composable
fun DownloadBoxConfirm(showDialog: MutableState<Boolean>) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showDialog.value = false },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x5590ee90),
                contentColor = Color.Green
            ),
            modifier = Modifier.size(250.dp, 50.dp)
        ) {
            Text("Hide", fontWeight = FontWeight.SemiBold)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("Range")
fun observeDownloadProgress(downloadId: Long, downloadManager: DownloadManager, onProgress: (Int) -> Unit) {
    GlobalScope.launch {
        var downloading = true
        while (downloading) {
            val query = DownloadManager.Query().setFilterById(downloadId)
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val bytesDownloaded =
                    cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val bytesTotal =
                    cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                val progress =
                    if (bytesTotal != -1) ((bytesDownloaded * 100L) / bytesTotal).toInt() else 0
                onProgress(progress)
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL)
                    downloading = false
            }
            cursor.close()
            delay(1000)
        }
    }
}


