package com.example.movieapp.ui.DetailsPlay

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.movieapp.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun sheetToDisplayDownload() {
    Column(
        Modifier
            .height(400.dp)
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material.Text(
            text = "Download",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(15.dp)
        )
        Divider(thickness = 1.dp, color = Color.LightGray)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Episodes",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20   .sp
            )
        }
        LazyRow(){
            items(5){ item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .size(150.dp, 125.dp)
                        .padding(5.dp)
                ){
                    Box(){
                        Image(
                            painter = painterResource(id = R.drawable.attackontitan),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                        )
                        Text(
                            text = "Episode $item",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp)
                            )
                    }
                }
            }
        }
        Divider(
            thickness = 0.5.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(0.dp, 10.dp)
        )
        Row(){
            FilledTonalButton(
                onClick = { /*TODO*/ },
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
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            FilledTonalButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(5.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "Download",
                    modifier = Modifier
                        .padding(start = 3.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}


@Composable
fun DownloadMain() {
    val context = LocalContext.current
    val downloadManager = remember { context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager }

    val downloadId = remember { mutableLongStateOf(0L) }
    val progress = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = progress.intValue / 100f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(onClick = {
            downloadId.longValue = downloadFile("https://cc.animeheaven.me/video.mp4?0fb4c0401567a7f326fad568ff1713d4&d", context)
        }) {
            Text("Start Download")
        }

        LaunchedEffect(downloadId.longValue){
            observeDownloadProgress(downloadId.longValue, downloadManager) { currentProgress ->
                progress.intValue = currentProgress
            }
        }
    }
}

fun downloadFile(url: String, context: Context): Long {
    val downloadManager = context.getSystemService(DownloadManager::class.java)

    val request = DownloadManager.Request(url.toUri())
        .setMimeType("video/mp4")
        .setAllowedOverRoaming(false)
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setTitle("video")
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS, "video.mp4"
        )
    return downloadManager.enqueue(request)
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


