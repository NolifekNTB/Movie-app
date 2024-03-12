package com.example.movieapp.core.BottomNavMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import br.com.frazo.compose_resources.IconResource
import com.example.movieapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: IconResource
) {
    data object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = IconResource.fromImageVector(Icons.Default.Home)
    )

    data object ReleaseCalendar : BottomBarScreen(
        route = "Calendar",
        title = "Calendar",
        icon = IconResource.fromImageVector(Icons.Default.DateRange)
    )
    data object MyList : BottomBarScreen(
        route = "MyList",
        title = "MyList",
        icon = IconResource.fromImageVector(Icons.AutoMirrored.Filled.List)
    )
    data object Download : BottomBarScreen(
        route = "Download",
        title = "Download",
        icon = IconResource.fromDrawableResource(R.drawable.profile_download)
    )

    data object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "PROFILE",
        icon = IconResource.fromImageVector(Icons.Default.Person)
    )
}
