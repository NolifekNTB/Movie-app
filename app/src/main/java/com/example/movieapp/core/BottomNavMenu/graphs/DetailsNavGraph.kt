package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movieapp.Details.ui.DetailScreen
import com.example.movieapp.Details.ui.DetailsScreens.CommentsScreen

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
    }
}

sealed class DetailsScreen(val route: String) {
    data object Details : DetailsScreen(route = "DETAILS")
    data object Comments : DetailsScreen(route = "COMMENTS")
}
