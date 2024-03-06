package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.Calendar.ui.ReleaseCalendar
import com.example.movieapp.Download.Download
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.MyList.ui.MyList
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
            MyList(){
                navController.popBackStack()
            }
        }

        composable(route = BottomBarScreen.Download.route) {
            Download(){
                navController.popBackStack()
            }
        }

        composable(route = BottomBarScreen.Profile.route) {
            Profile()
        }
    }
}

