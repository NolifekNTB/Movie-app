package com.example.movieapp.core.other

import  android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.Home.logic.viewModel.MainViewModel
import com.example.movieapp.core.MyList.logic.ListViewModel
import com.example.movieapp.core.BottomNavMenu.graphs.RootNavigationGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.fillMaxSize()) {
                val viewModel = hiltViewModel<ListViewModel>()
                RootNavigationGraph(navController = rememberNavController(), viewModel)
            }
        }
    }
}


