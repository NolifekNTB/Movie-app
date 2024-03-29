package com.example.movieapp.core.ui.BottomNavMenu.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.movieapp.features.Details.DetailScreen
import com.example.movieapp.features.Details.screens.CommentsScreen
import com.example.movieapp.features.Details.screens.VideoPlayer
import com.example.movieapp.shared.SharedViewModel

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController, viewModel: SharedViewModel) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Details.route
    ) {
        composable(
            route = "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailScreen(viewModel, id){ popUpOrNextScreen ->
                when(popUpOrNextScreen){
                    "popUp" -> navController.popBackStack()
                    "nextScreen" -> navController.navigate(DetailsScreen.Comments.route)
                    "videoPlayer" -> navController.navigate(DetailsScreen.Player.route)
                }
            }
        }
        composable(route = DetailsScreen.Comments.route){
            CommentsScreen(){
                navController.popBackStack(
                    route = DetailsScreen.Details.route,
                    inclusive = false
                )
            }
        }
        composable(route = DetailsScreen.Player.route){
            VideoPlayer(){
                navController.popBackStack(
                    route = DetailsScreen.Details.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    data object Details : DetailsScreen(route = "DETAILS")
    data object Comments : DetailsScreen(route = "COMMENTS")
    data object Player : DetailsScreen(route = "PLAYER")

}
