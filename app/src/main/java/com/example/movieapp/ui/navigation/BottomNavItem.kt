package com.example.movieapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    data object ReleaseDate : BottomNavItem("releaseDate", Icons.Sharp.DateRange, "Release Date")
    data object Mylist : BottomNavItem("Mylist", Icons.Outlined.List, "My List")
    data object Download : BottomNavItem("download", Icons.Default.Share , "Download")
    data object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}