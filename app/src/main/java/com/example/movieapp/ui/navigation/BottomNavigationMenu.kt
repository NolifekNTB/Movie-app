package com.example.movieapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.AnimeItem
import com.example.movieapp.ui.BottomNavMenu.Home.HomeMenu.NewEpisodeReleases
import com.example.movieapp.ui.BottomNavMenu.Home.HomeMenu.Notification
import com.example.movieapp.ui.BottomNavMenu.Home.HomeMenu.TopHitsAnime
import com.example.movieapp.ui.BottomNavMenu.Home.HomeScreen
import com.example.movieapp.ui.BottomNavMenu.Home.Search.Search
import com.example.movieapp.ui.BottomNavMenu.Home.Search.SortFilter
import com.example.movieapp.ui.BottomNavMenu.ReleaseCalendar

@Composable
fun BottomNavigationMenu(animeList: List<AnimeItem>) {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.ReleaseDate,
        BottomNavItem.Mylist,
        BottomNavItem.Download,
        BottomNavItem.Profile,
    )

    Scaffold(
        bottomBar = {
            Card(
                shape = RoundedCornerShape(topEnd = 18.dp)
            ){
                BottomNavigation(
                    backgroundColor = Color.Black
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    items.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = null,
                                    tint = if (selected)
                                        Color.Green else Color.Gray
                                ) },
                            label = {
                                Text(
                                    text = screen.label,
                                    color = Color.Gray,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    fontSize = 12.sp
                                ) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route)
                                {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController, animeList) }
            composable(BottomNavItem.ReleaseDate.route) { ReleaseCalendar(navController) }
            composable(BottomNavItem.Mylist.route) { }
            composable(BottomNavItem.Download.route) { }
            composable(BottomNavItem.Profile.route) { }
            composable("Top Hits Anime") { TopHitsAnime(navController) }
            composable("New Episode Releases") { NewEpisodeReleases(navController) }
            composable("Notification") { Notification(navController) }
            composable("Search") { Search(navController) }
            composable("sortFilter") { SortFilter(navController) }
        }
    }
}






