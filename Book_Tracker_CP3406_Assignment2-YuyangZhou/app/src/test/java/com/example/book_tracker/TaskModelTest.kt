package com.example.book_tracker

import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.model.TaskCategory
import com.example.book_tracker.domain.model.TaskPriority
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDateTime

/**
 * Unit tests for Task domain model
 */
class TaskModelTest {

    @Test
    fun task_creation_isCorrect() {
        val task = Task(
            id = 1,
            title = "Test Task",
            courseCode = "CP3406",
            courseName = "Database Systems",
            dueDate = LocalDateTime.now(),
            isCompleted = false,
            priority = TaskPriority.HIGH,
            category = TaskCategory.ASSIGNMENT
        )

        assertEquals(1, task.id)
        assertEquals("Test Task", task.title)
        assertEquals("CP3406", task.courseCode)
        assertEquals(TaskPriority.HIGH, task.priority)
        assertFalse(task.isCompleted)
    }

    @Test
    fun task_copy_changesCompleted() {
        val task = Task(
            id = 1,
            title = "Test Task",
            courseCode = "CP3406",
            courseName = "Database Systems",
            dueDate = LocalDateTime.now(),
            isCompleted = false,
            priority = TaskPriority.HIGH,
            category = TaskCategory.ASSIGNMENT
        )

        val completedTask = task.copy(isCompleted = true)

        assertFalse(task.isCompleted)
        assertTrue(completedTask.isCompleted)
        assertEquals(task.id, completedTask.id)
        assertEquals(task.title, completedTask.title)
    }

    @Test
    fun taskPriority_hasCorrectValues() {
        assertEquals(4, TaskPriority.values().size)
        assertTrue(TaskPriority.values().contains(TaskPriority.URGENT))
        assertTrue(TaskPriority.values().contains(TaskPriority.HIGH))
        assertTrue(TaskPriority.values().contains(TaskPriority.MEDIUM))
        assertTrue(TaskPriority.values().contains(TaskPriority.LOW))
    }

    @Test
    fun taskCategory_hasCorrectValues() {
        assertEquals(6, TaskCategory.values().size)
        assertTrue(TaskCategory.values().contains(TaskCategory.ASSIGNMENT))
        assertTrue(TaskCategory.values().contains(TaskCategory.QUIZ))
        assertTrue(TaskCategory.values().contains(TaskCategory.EXAM))
        assertTrue(TaskCategory.values().contains(TaskCategory.PROJECT))
        assertTrue(TaskCategory.values().contains(TaskCategory.READING))
        assertTrue(TaskCategory.values().contains(TaskCategory.OTHER))
    }
}
