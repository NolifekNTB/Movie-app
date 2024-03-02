package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.Home.ui.HomeScreen
import com.example.movieapp.Home.ui.HomeScreens.NewEpisodeReleases
import com.example.movieapp.Home.ui.HomeScreens.Notification.Notification
import com.example.movieapp.Home.ui.HomeScreens.Search.Search
import com.example.movieapp.Home.ui.HomeScreens.Search.sortFilter.SortFilter
import com.example.movieapp.Home.ui.HomeScreens.TopHitsAnime.TopHitsAnime
import com.example.movieapp.core.BottomNavMenu.BottomBarScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    navigation(
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onClick = { direction ->
                    navController.navigate(direction)
                })
        }

        composable(route = "Notification"){
            Notification(){
                navController.popBackStack()
            }
        }
        composable(route = "Search"){
            Search(viewModel = viewModel){
                navController.navigate("sortFilter")
            }
        }

        composable(route = "SortFilter"){
            SortFilter(
                onClick = { navController.popBackStack() },
                viewModel = viewModel
            )
        }

        composable(route = "Top Hits Anime"){
            TopHitsAnime {
                navController.popBackStack()
            }
        }

        composable(route = "New Episodes Releases"){
            NewEpisodeReleases {
                navController.popBackStack()
            }
        }

        detailsNavGraph(navController = navController)
    }
}