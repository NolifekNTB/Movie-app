package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.core.BottomNavMenu.BottomBarScaffold

@Composable
fun RootNavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.BOTTOM
    ) {
        composable(route = Graph.BOTTOM) {
            BottomBarScaffold(viewModel)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val BOTTOM = "bottom_graph"
    const val DETAILS = "details_graph"
}