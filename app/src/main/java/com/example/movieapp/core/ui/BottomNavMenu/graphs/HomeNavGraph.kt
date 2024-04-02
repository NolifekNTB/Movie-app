package com.example.movieapp.core.ui.BottomNavMenu.graphs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.movieapp.core.ui.BottomNavMenu.BottomBarScreen
import com.example.movieapp.features.Details.screens.VideoPlayerWebView
import com.example.movieapp.features.Home.domain.MainViewModel
import com.example.movieapp.features.Home.ui.HomeScreen
import com.example.movieapp.features.Home.ui.NewSeasonsReleases.NewSeasonsReleases
import com.example.movieapp.features.Home.ui.Notification.Notification
import com.example.movieapp.features.Home.domain.SearchViewModel
import com.example.movieapp.features.Home.ui.Search.Search
import com.example.movieapp.features.Home.ui.Search.sortFilter.SortFilter
import com.example.movieapp.features.Home.ui.TopHitsAnime.TopHitsAnime
import com.example.movieapp.shared.SharedViewModel

fun NavGraphBuilder.homeNavGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {
    navigation(
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route) { entry ->
            val mainViewModel = entry.sharedViewModelSearch<MainViewModel>(navController)
            HomeScreen(
                mainViewModel = mainViewModel,
                sharedViewModel = sharedViewModel,
                onClick = { direction, id ->
                    navController.navigate("$direction/$id")
                })
        }

        composable(route = "webView/{url}"){
            VideoPlayerWebView()
        }

        composable(route = "Notification"){
            Notification(){
                navController.popBackStack()
            }
        }
        composable(route = "Search"){entry ->
            val viewModelSearch = entry.sharedViewModelSearch<SearchViewModel>(navController)
            Search(viewModel = viewModelSearch){
                navController.navigate("sortFilter")
            }
        }

        composable(route = "SortFilter"){ entry ->
            val viewModelSearch = entry.sharedViewModelSearch<SearchViewModel>(navController)
            SortFilter(
                onClick = { navController.popBackStack() },
                viewModel = viewModelSearch
            )
        }

        composable(route = "Top Hits Anime"){entry ->
            val mainViewModel = entry.sharedViewModelSearch<MainViewModel>(navController)
            TopHitsAnime(mainViewModel, sharedViewModel) {what ->
                if(what == "Back") navController.popBackStack()
            }
        }

        composable(route = "New Seasons Releases"){
            NewSeasonsReleases (
                onClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModelSearch(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}










