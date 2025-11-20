package com.example.book_tracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.book_tracker.presentation.analytics.AnalyticsScreen
import com.example.book_tracker.presentation.calendar.CalendarScreen
import com.example.book_tracker.presentation.dashboard.DashboardScreen
import com.example.book_tracker.presentation.profile.ProfileScreen
import com.example.book_tracker.presentation.tasks.TasksScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Dashboard.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }

        composable(Screen.Tasks.route) {
            TasksScreen(navController = navController)
        }

        composable(Screen.Calendar.route) {
            CalendarScreen(navController = navController)
        }

        composable(Screen.Analytics.route) {
            AnalyticsScreen(navController = navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}
