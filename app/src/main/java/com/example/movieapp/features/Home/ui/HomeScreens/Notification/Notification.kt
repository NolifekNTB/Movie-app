package com.example.movieapp.features.Home.ui.HomeScreens.Notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.shared.TopBar

@Composable
fun Notification(onClick: () -> Unit) {
    Column(
        modifier = Modifier
           .fillMaxSize()
           .background(Color.White)
    ) {
        TopBar(title = "Notification"){ _ -> onClick()}
        NotificationList()
    }
}

@Composable
fun NotificationList(){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(7){
           NotificationListItem()
        }
    }
}

@Composable
fun NotificationListItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        NotificationImage()
        NotificationDetails()
        NotificationDate()
    }
}