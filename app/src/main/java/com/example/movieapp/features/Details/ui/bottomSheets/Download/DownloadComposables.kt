package com.example.movieapp.features.Details.ui.bottomSheets.Download

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.features.Details.domain.DetailsViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun downloadBox(
    showDialog: MutableState<Boolean>,
    startDownloadPhoto: MutableState<Boolean>,
    viewModel: DetailsViewModel
) {
    val context = LocalContext.current
    val downloadManager = remember { context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager }

    val downloadId = remember { mutableLongStateOf(0L) }
    val progress = remember { mutableIntStateOf(0) }

    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        text = {
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
        },
        confirmButton = {
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
        },
        modifier = Modifier.padding(16.dp)
    )
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


