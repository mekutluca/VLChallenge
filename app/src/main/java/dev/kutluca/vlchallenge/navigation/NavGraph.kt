package dev.kutluca.vlchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kutluca.vlchallenge.ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Home.path,
    ) {
        composable(Route.Home.path) {
            HomeScreen()
        }
    }
}

sealed class Route(val path: String) {
    data object Home : Route("home")
}