package com.example.movieapp.core.ui.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.movieapp.features.Profile.domain.ProfileViewModel
import com.example.movieapp.features.Profile.ui.Profile
import com.example.movieapp.features.Profile.ui.screens.Settings.settings
import com.example.movieapp.features.Profile.ui.screens.Subscribe.addNewCard
import com.example.movieapp.features.Profile.ui.screens.Subscribe.payment
import com.example.movieapp.features.Profile.ui.screens.Subscribe.reviewSummary
import com.example.movieapp.features.Profile.ui.screens.Subscribe.subscribe

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
                    "back" -> navController.popBackStack()
                }
            }
        }

        composable(route = ProfileScreen.Subscribe.route){
            subscribe(){where ->
                when(where){
                    "back" -> navController.popBackStack()
                    "next" -> navController.navigate("payment")
                }
            }
        }
        composable(route = ProfileScreen.Settings.route){
            settings(){
                navController.popBackStack()
            }
        }

        composable(route = "payment"){ entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navController)

            payment(viewModel){ where ->
                when(where){
                    "addNewCard" -> navController.navigate("addNewCard")
                    "continue" -> navController.navigate("reviewSummary")
                    "back" -> navController.popBackStack()
                }
            }
        }

        composable(route = "addNewCard"){ entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navController)

            addNewCard(viewModel){ where ->
                when(where){
                    "back" -> navController.popBackStack()
                    "add" -> navController.navigate("payment")
                }
            }
        }

        composable(route = "reviewSummary"){ entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navController)

            reviewSummary(viewModel){
                navController.popBackStack()
            }
        }
    }
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

sealed class ProfileScreen(val route: String) {
    data object Profile : ProfileScreen(route = "PROFILE")
    data object Subscribe : ProfileScreen(route = "SUBSCRIBE")
    data object Settings : ProfileScreen(route = "SETTINGS")
}