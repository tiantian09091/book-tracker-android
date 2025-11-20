package com.example.book_tracker

import com.example.book_tracker.presentation.tasks.TaskFilter
import com.example.book_tracker.presentation.tasks.TasksUiState
import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for TasksViewModel state
 */
class TasksViewModelStateTest {

    @Test
    fun tasksUiState_defaultValues_areCorrect() {
        val state = TasksUiState()

        assertTrue(state.tasks.isEmpty())
        assertEquals(TaskFilter.ALL, state.selectedFilter)
        assertFalse(state.isLoading)
    }

    @Test
    fun tasksUiState_copy_changesFilter() {
        val state = TasksUiState()
        val newState = state.copy(selectedFilter = TaskFilter.DUE_TODAY)

        assertEquals(TaskFilter.ALL, state.selectedFilter)
        assertEquals(TaskFilter.DUE_TODAY, newState.selectedFilter)
    }

    @Test
    fun taskFilter_hasAllValues() {
        val filters = TaskFilter.values()

        assertEquals(5, filters.size)
        assertTrue(filters.contains(TaskFilter.ALL))
        assertTrue(filters.contains(TaskFilter.DUE_TODAY))
        assertTrue(filters.contains(TaskFilter.THIS_WEEK))
        assertTrue(filters.contains(TaskFilter.OVERDUE))
        assertTrue(filters.contains(TaskFilter.COMPLETED))
    }
}
