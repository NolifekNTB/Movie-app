package com.example.movieapp.Profile.ProfileScreens.Settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun settings(onClick: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Settings")
    }
}