package com.example.movieapp.core.ui.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.features.MyList.ui.MyList
import com.example.movieapp.core.ui.BottomNavMenu.BottomBarScreen
import com.example.movieapp.features.Calendar.ui.ReleaseCalendar
import com.example.movieapp.features.Download.ui.Download
import com.example.movieapp.shared.SharedViewModel

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: SharedViewModel) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM,
        startDestination = Graph.HOME
    ) {
        homeNavGraph(navController = navController, viewModel)

        composable(route = BottomBarScreen.ReleaseCalendar.route) {
            ReleaseCalendar(){
                navController.popBackStack()
            }
        }

        composable(route = BottomBarScreen.MyList.route) {
            MyList(viewModel){ where ->
                if(where == "Back") navController.popBackStack()
            }
        }

        composable(route = BottomBarScreen.Download.route) {
            Download(){
                navController.popBackStack()
            }
        }

        profileNavGraph(navController = navController)

        detailsNavGraph(navController = navController, viewModel)
    }
}

