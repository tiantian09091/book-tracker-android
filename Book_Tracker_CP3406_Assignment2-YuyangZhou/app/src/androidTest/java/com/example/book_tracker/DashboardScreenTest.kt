package com.example.book_tracker

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.book_tracker.presentation.dashboard.DashboardScreenContent
import com.example.book_tracker.presentation.dashboard.DashboardUiState
import com.example.book_tracker.ui.theme.Book_TrackerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI Tests for DashboardScreen
 */
@RunWith(AndroidJUnit4::class)
class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val sampleState = DashboardUiState(
        dueToday = 2,
        plannedStudyHours = 3.0,
        weekProgress = 75,
        quote = "Keep pushing forward.",
        quoteAuthor = "Unknown"
    )

    @Test
    fun dashboardScreen_displaysTitle() {
        composeTestRule.setContent {
            Book_TrackerTheme {
                DashboardScreenContent(
                    uiState = sampleState
                )
            }
        }

        composeTestRule.onNodeWithText("Welcome back, Sarah!").assertIsDisplayed()
    }

    @Test
    fun dashboardScreen_displaysTodaysFocus() {
        composeTestRule.setContent {
            Book_TrackerTheme {
                DashboardScreenContent(
                    uiState = sampleState
                )
            }
        }

        composeTestRule.onNodeWithText("Today's Focus").assertIsDisplayed()
    }

    @Test
    fun dashboardScreen_displaysUpcomingDeadlines() {
        composeTestRule.setContent {
            Book_TrackerTheme {
                DashboardScreenContent(
                    uiState = sampleState
                )
            }
        }

        composeTestRule.onNodeWithText("Upcoming Deadlines").assertIsDisplayed()
    }

    @Test
    fun dashboardScreen_displaysQuickActions() {
        composeTestRule.setContent {
            Book_TrackerTheme {
                DashboardScreenContent(
                    uiState = sampleState
                )
            }
        }

        composeTestRule.onNodeWithText("Add Task").assertIsDisplayed()
        composeTestRule.onNodeWithText("Start Session").assertIsDisplayed()
    }

    @Test
    fun dashboardScreen_quickActionButtons_areClickable() {
        composeTestRule.setContent {
            Book_TrackerTheme {
                DashboardScreenContent(
                    uiState = sampleState
                )
            }
        }

        // Test that buttons can be clicked without crash
        composeTestRule.onNodeWithText("Add Task").performClick()
        composeTestRule.onNodeWithText("Add Task").assertIsDisplayed()
    }
}
