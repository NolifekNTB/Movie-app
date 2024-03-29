package com.example.movieapp.core.ui.BottomNavMenu.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movieapp.features.Details.ui.DetailScreen
import com.example.movieapp.features.Details.ui.screens.CommentsScreen
import com.example.movieapp.features.Details.ui.screens.VideoPlayer

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Details.route
    ) {
        composable(route = DetailsScreen.Details.route) {
            DetailScreen(){ popUpOrNextScreen ->
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
