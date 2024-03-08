package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.movieapp.Profile.Profile
import com.example.movieapp.Profile.ProfileScreens.Settings.settings
import com.example.movieapp.Profile.ProfileScreens.Subscribe.subscribe

fun NavGraphBuilder.profileNavGraph(navController: NavHostController){
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreen.Profile.route
    ){
        composable(route = ProfileScreen.Profile.route){
            Profile() { where ->
                when(where){
                    "premium" -> navController.navigate(ProfileScreen.Subscribe.route)
                    "settings" -> navController.navigate(ProfileScreen.Settings.route)
                }
            }
        }

        composable(route = ProfileScreen.Subscribe.route){
            subscribe(){
                navController.popBackStack()
            }
        }
        composable(route = ProfileScreen.Settings.route){
            settings(){
                navController.popBackStack()
            }
        }
    }
}

sealed class ProfileScreen(val route: String) {
    data object Profile : ProfileScreen(route = "PROFILE")
    data object Subscribe : ProfileScreen(route = "SUBSCRIBE")
    data object Settings : ProfileScreen(route = "SETTINGS")
}