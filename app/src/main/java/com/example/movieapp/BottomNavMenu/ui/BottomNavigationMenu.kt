package com.example.movieapp.BottomNavMenu.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.movieapp.BottomNavMenu.data.BottomNavItem
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.Home.data.room.AnimeItem
import com.example.movieapp.Home.ui.HomeScreens.NewEpisodeReleases
import com.example.movieapp.Home.ui.HomeScreens.Notification.Notification
import com.example.movieapp.Home.ui.HomeScreens.TopHitsAnime.TopHitsAnime
import com.example.movieapp.Home.ui.HomeScreen
import com.example.movieapp.Home.ui.HomeScreens.Search.Search
import com.example.movieapp.Home.ui.HomeScreens.Search.sortFilter.SortFilter
import com.example.movieapp.Calendar.ui.ReleaseCalendar
import com.example.movieapp.Details.ui.CommentsScreen
import com.example.movieapp.Details.ui.Details.DetailScreen


fun NavGraphBuilder.homeGraph(navController: NavController, animeList: List<AnimeItem>, viewModel: MainViewModel) {
    navigation(startDestination = "home", route = "homeRoute") {
        composable("home") { HomeScreen(
            onPlayClicked = {s -> navController.navigate(s)},
            animeList
        )
        }
        composable("search") { Search(navController, viewModel) }
        composable("sortFilter") { SortFilter(navController, viewModel) }

        composable("notification") { Notification(navController) }

        composable("Top Hits Anime") { TopHitsAnime(navController) }
        composable("New Episode Releases") { NewEpisodeReleases(navController) }
    }
}

fun NavGraphBuilder.calendarGraph(navController: NavController) {
    navigation(startDestination = "releaseDate", route = "releaseDateRoute") {
        composable("releaseDate") { ReleaseCalendar(navController) }
    }
}


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationMenu(animeList: List<AnimeItem>, viewModel: MainViewModel) {
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
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
        ) {
            //Default
            composable("home") { HomeScreen(
                onPlayClicked = {s -> navController.navigate(s)},
                animeList
            )
            }

            homeGraph(navController, animeList, viewModel)
            calendarGraph(navController)
            composable(BottomNavItem.Mylist.route) { DetailScreen(navController) }
            composable(BottomNavItem.Download.route) {}
            composable(BottomNavItem.Profile.route) {}
            composable("comments") { CommentsScreen() }
        }
    }

}







