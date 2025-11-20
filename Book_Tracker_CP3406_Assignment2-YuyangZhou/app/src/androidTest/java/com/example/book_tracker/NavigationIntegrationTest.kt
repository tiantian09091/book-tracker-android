package com.example.book_tracker

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration tests for app navigation using Activity
 */
@RunWith(AndroidJUnit4::class)
class NavigationIntegrationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun bottomNavigation_displaysAllItems() {
        // Check all navigation items are present
        composeTestRule.onNodeWithText("Dashboard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tasks").assertIsDisplayed()
        composeTestRule.onNodeWithText("Calendar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Analytics").assertIsDisplayed()
        composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
    }

    @Test
    fun bottomNavigation_navigateToTasks() {
        // Navigate to Tasks
        composeTestRule.onNodeWithText("Tasks").performClick()
        composeTestRule.waitForIdle()

        // Verify Tasks screen is displayed
        composeTestRule.onNodeWithText("All").assertIsDisplayed()
        composeTestRule.onNodeWithText("Due Today").assertIsDisplayed()
    }

    @Test
    fun bottomNavigation_navigateToProfile() {
        // Navigate to Profile
        composeTestRule.onNodeWithText("Profile").performClick()
        composeTestRule.waitForIdle()

        // Verify Profile screen is displayed
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }

    @Test
    fun bottomNavigation_navigateToCalendar() {
        // Navigate to Calendar
        composeTestRule.onNodeWithText("Calendar").performClick()
        composeTestRule.waitForIdle()

        // Verify Calendar screen is displayed (check for "Week" filter)
        composeTestRule.onNodeWithText("Week").assertIsDisplayed()
    }

    @Test
    fun bottomNavigation_navigateToAnalytics() {
        // Navigate to Analytics
        composeTestRule.onNodeWithText("Analytics").performClick()
        composeTestRule.waitForIdle()

        // Verify Analytics screen is displayed
        composeTestRule.onNodeWithText("Weekly Study Pattern").assertIsDisplayed()
    }
}
