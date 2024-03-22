package com.example.movieapp.core.BottomNavMenu.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.Home.ui.HomeScreen
import com.example.movieapp.Home.ui.HomeScreens.NewSeasonsReleases
import com.example.movieapp.Home.ui.HomeScreens.Notification.Notification
import com.example.movieapp.Home.ui.HomeScreens.Search.Search
import com.example.movieapp.Home.ui.HomeScreens.Search.logic.SearchViewModel
import com.example.movieapp.Home.ui.HomeScreens.Search.sortFilter.SortFilter
import com.example.movieapp.Home.ui.HomeScreens.TopHitsAnime.TopHitsAnime
import com.example.movieapp.core.MyList.logic.ListViewModel
import com.example.movieapp.core.BottomNavMenu.BottomBarScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController, listViewModel: ListViewModel) {
    navigation(
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route) {entry ->
            val mainViewModel = entry.sharedViewModelSearch<MainViewModel>(navController)
            HomeScreen(
                mainViewModel = mainViewModel,
                sharedViewModel = listViewModel,
                onClick = { direction ->
                    navController.navigate(direction)
                })
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

        composable(route = "Top Hits Anime"){
            TopHitsAnime {
                navController.popBackStack()
            }
        }

        composable(route = "New Episodes Releases"){
            NewSeasonsReleases (
                onClick = { navController.popBackStack() }
            )
        }

        detailsNavGraph(navController = navController)
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










