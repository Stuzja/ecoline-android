package com.example.ecoline.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecoline.ui.screens.calendar.CalendarScreen
import com.example.ecoline.ui.screens.map.MapScreen
import com.example.ecoline.ui.screens.home.HomeScreen
import com.example.ecoline.ui.screens.feed.FeedScreen
import com.example.ecoline.ui.utils.BottomNavigationBar
import com.example.ecoline.ui.utils.Screens

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController, backStackEntry = backStackEntry)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            startDestination = Screens.Home.route,
            navController = navController,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            composable(Screens.Home.route) {
                HomeScreen()
            }

            composable(Screens.Map.route) {
                MapScreen()
            }
            composable(Screens.Calendar.route) {
                CalendarScreen()
            }

            composable(Screens.Feed.route) {
                FeedScreen()
            }
        }
    }
}