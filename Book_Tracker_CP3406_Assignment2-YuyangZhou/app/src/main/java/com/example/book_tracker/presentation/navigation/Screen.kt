package com.example.book_tracker.presentation.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Tasks : Screen("tasks")
    object Calendar : Screen("calendar")
    object Analytics : Screen("analytics")
    object Profile : Screen("profile")
    object AddTask : Screen("add_task")
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: Long) = "task_detail/$taskId"
    }
}
