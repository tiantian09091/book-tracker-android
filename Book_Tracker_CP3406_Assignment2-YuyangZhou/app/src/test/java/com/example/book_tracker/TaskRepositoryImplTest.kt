package com.example.book_tracker.data.repository

import com.example.book_tracker.data.database.TaskDao
import com.example.book_tracker.data.database.TaskEntity
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.model.TaskCategory
import com.example.book_tracker.domain.model.TaskPriority
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class TaskRepositoryImplTest {

    private lateinit var taskDao: TaskDao
    private lateinit var repository: TaskRepositoryImpl

    @Before
    fun setup() {
        taskDao = mockk(relaxed = true)
        repository = TaskRepositoryImpl(taskDao)
    }

    @Test
    fun `getAllTasks returns list of tasks`() = runTest {
        // Given
        val taskEntities = listOf(
            TaskEntity(
                id = 1,
                title = "Test Task",
                description = "Test Description",
                courseCode = "CP3406",
                courseName = "Mobile Development",
                dueDate = LocalDateTime.now().toString(),
                isCompleted = false,
                priority = "MEDIUM",
                category = "ASSIGNMENT",
                createdAt = LocalDateTime.now().toString(),
                updatedAt = LocalDateTime.now().toString()
            )
        )
        coEvery { taskDao.getAllTasks() } returns flowOf(taskEntities)

        // When
        val result = repository.getAllTasks().first()

        // Then
        assertThat(result).hasSize(1)
        assertThat(result[0].title).isEqualTo("Test Task")
        assertThat(result[0].courseCode).isEqualTo("CP3406")
    }

    @Test
    fun `insertTask calls dao insertTask`() = runTest {
        // Given
        val task = Task(
            title = "New Task",
            description = "Description",
            courseCode = "CP3406",
            courseName = "Mobile Development",
            dueDate = LocalDateTime.now(),
            priority = TaskPriority.HIGH,
            category = TaskCategory.ASSIGNMENT
        )
        coEvery { taskDao.insertTask(any()) } returns 1L

        // When
        val result = repository.insertTask(task)

        // Then
        assertThat(result).isEqualTo(1L)
        coVerify { taskDao.insertTask(any()) }
    }

    @Test
    fun `getActiveTasks returns only incomplete tasks`() = runTest {
        // Given
        val activeTaskEntities = listOf(
            TaskEntity(
                id = 1,
                title = "Active Task",
                description = "",
                courseCode = "CP3406",
                courseName = "Mobile Development",
                dueDate = LocalDateTime.now().toString(),
                isCompleted = false,
                priority = "HIGH",
                category = "ASSIGNMENT",
                createdAt = LocalDateTime.now().toString(),
                updatedAt = LocalDateTime.now().toString()
            )
        )
        coEvery { taskDao.getActiveTasks() } returns flowOf(activeTaskEntities)

        // When
        val result = repository.getActiveTasks().first()

        // Then
        assertThat(result).hasSize(1)
        assertThat(result[0].isCompleted).isFalse()
    }

    @Test
    fun `deleteTaskById calls dao deleteTaskById`() = runTest {
        // Given
        val taskId = 1L
        coEvery { taskDao.deleteTaskById(taskId) } returns Unit

        // When
        repository.deleteTaskById(taskId)

        // Then
        coVerify { taskDao.deleteTaskById(taskId) }
    }

    @Test
    fun `getTaskById returns task when exists`() = runTest {
        // Given
        val taskId = 1L
        val taskEntity = TaskEntity(
            id = taskId,
            title = "Existing Task",
            description = "",
            courseCode = "CP3406",
            courseName = "Mobile Development",
            dueDate = LocalDateTime.now().toString(),
            isCompleted = false,
            priority = "MEDIUM",
            category = "ASSIGNMENT",
            createdAt = LocalDateTime.now().toString(),
            updatedAt = LocalDateTime.now().toString()
        )
        coEvery { taskDao.getTaskById(taskId) } returns taskEntity

        // When
        val result = repository.getTaskById(taskId)

        // Then
        assertThat(result).isNotNull()
        assertThat(result?.id).isEqualTo(taskId)
        assertThat(result?.title).isEqualTo("Existing Task")
    }

    @Test
    fun `getTaskById returns null when task does not exist`() = runTest {
        // Given
        val taskId = 999L
        coEvery { taskDao.getTaskById(taskId) } returns null

        // When
        val result = repository.getTaskById(taskId)

        // Then
        assertThat(result).isNull()
    }
}
