package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movieapp.Calendar.ui.ReleaseCalendar
import com.example.movieapp.Download.Download
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.Home.ui.HomeScreen
import com.example.movieapp.Home.ui.HomeScreens.Search.Search
import com.example.movieapp.MyList.MyList
import com.example.movieapp.Profile.Profile
import com.example.movieapp.core.BottomNavMenu.BottomBarScreen

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM,
        startDestination = Graph.HOME
    ) {

        homeNavGraph(navController = navController, viewModel = viewModel)

        composable(route = BottomBarScreen.ReleaseCalendar.route) {
            ReleaseCalendar(){
                navController.popBackStack()
            }
        }

        composable(route = BottomBarScreen.MyList.route) {
            MyList()
        }

        composable(route = BottomBarScreen.Download.route) {
            Download()
        }

        composable(route = BottomBarScreen.Profile.route) {
            Profile()
        }
    }
}

