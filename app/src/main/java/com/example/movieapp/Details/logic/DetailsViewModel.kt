package com.example.movieapp.Details.logic

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {
    fun downloadFile(url: String, context: Context): Long {
        val downloadManager = context.getSystemService(DownloadManager::class.java)

        val request = DownloadManager.Request(url.toUri())
            .setMimeType("photo/jpeg")
            .setAllowedOverRoaming(false)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("jpeg")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, "photo.jpeg"
            )
        return downloadManager.enqueue(request)
    }
}