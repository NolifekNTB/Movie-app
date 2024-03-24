package com.example.movieapp.core.ui.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.features.MyList.domain.ListViewModel
import com.example.movieapp.core.ui.BottomNavMenu.BottomBarScaffold

@Composable
fun RootNavigationGraph(navController: NavHostController, viewModel: ListViewModel) {
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
    const val PROFILE = "profile_graph"
}