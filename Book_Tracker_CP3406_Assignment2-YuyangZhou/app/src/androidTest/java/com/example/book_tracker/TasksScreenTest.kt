package com.example.book_tracker

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.book_tracker.presentation.tasks.TasksScreen
import com.example.book_tracker.ui.theme.Book_TrackerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI Tests for TasksScreen using Jetpack Compose Testing
 */
@RunWith(AndroidJUnit4::class)
class TasksScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tasksScreen_displaysTitle() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Tasks").assertIsDisplayed()
    }

    @Test
    fun tasksScreen_displaysFilterChips() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // Then
        composeTestRule.onNodeWithTag("filter_chip_ALL").assertIsDisplayed()
        composeTestRule.onNodeWithTag("filter_chip_DUE_TODAY").assertIsDisplayed()
        composeTestRule.onNodeWithTag("filter_chip_THIS_WEEK").assertIsDisplayed()
        composeTestRule.onNodeWithTag("filter_chip_OVERDUE").assertIsDisplayed()
        composeTestRule.onNodeWithTag("filter_chip_COMPLETED").assertIsDisplayed()
    }

    @Test
    fun tasksScreen_displaysTaskCards() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // Then - Check that sample tasks are displayed
        composeTestRule.onNodeWithText("Complete React Native Quiz").assertIsDisplayed()
        composeTestRule.onNodeWithText("Database Design Assignment").assertIsDisplayed()
    }

    @Test
    fun tasksScreen_filterChip_changesFilter() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // When - Click on "Completed" filter
        composeTestRule.onNodeWithTag("filter_chip_COMPLETED").performClick()

        // Then - Should show completed tasks
        composeTestRule.onNodeWithText("Read Chapter 5 - Algorithms").assertIsDisplayed()
    }

    @Test
    fun tasksScreen_addButton_isDisplayed() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // Then
        composeTestRule.onNodeWithContentDescription("Add Task").assertIsDisplayed()
    }

    @Test
    fun tasksScreen_taskCard_canBeClicked() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // When - Click on a task card
        composeTestRule.onNodeWithText("Complete React Native Quiz").performClick()

        // Then - Should be clickable (no crash)
        composeTestRule.onNodeWithText("Complete React Native Quiz").assertIsDisplayed()
    }

    @Test
    fun tasksScreen_overdueFilter_showsOverdueTasks() {
        // Given
        composeTestRule.setContent {
            Book_TrackerTheme {
                val navController = rememberNavController()
                TasksScreen(navController = navController)
            }
        }

        // When - Click on "Overdue" filter
        composeTestRule.onNodeWithTag("filter_chip_OVERDUE").performClick()

        // Then - Should show overdue tasks
        composeTestRule.onNodeWithText("Complete Lab Report").assertIsDisplayed()
    }
}
