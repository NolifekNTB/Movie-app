package com.example.movieapp.shared

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.database.entities.AnimeItemMyList
import com.example.movieapp.core.database.entities.AnimeItemTopHits
import com.example.movieapp.features.Home.data.repositories.AnimeRepository
import com.example.movieapp.features.MyList.data.AnimeRepositoryMyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repoMyList: AnimeRepositoryMyList,
    private val repoTopHis: AnimeRepository
): ViewModel() {
    fun getTopHits(): Flow<List<AnimeItemTopHits>> = repoTopHis.getAllAnime()
    fun getListMyList(): Flow<List<AnimeItemMyList>> = repoMyList.getAllAnime()

    private fun insertListMyList(animeMyList: AnimeItemMyList){
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoMyList.insertAnime(animeMyList)
        }
    }

    fun insertItemMyList(item: AnimeItemMyList){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repoMyList.insertAnime(item)
        }
    }

    private fun deleteListMyList(){
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repoMyList.deleteALlAnime()
        }
    }

    fun deleteItemMyList(animeItem: AnimeItemMyList){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repoMyList.deleteAnime(animeItem)
        }
    }

    suspend fun searchItemMyList(query: String): List<AnimeItemMyList> {
        return repoMyList.searchAnimeByName(query)
    }

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

















