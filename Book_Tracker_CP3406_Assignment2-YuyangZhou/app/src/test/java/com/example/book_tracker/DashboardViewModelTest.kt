package com.example.book_tracker.presentation.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.book_tracker.data.network.QuoteApiService
import com.example.book_tracker.data.network.QuoteResponse
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.model.TaskCategory
import com.example.book_tracker.domain.model.TaskPriority
import com.example.book_tracker.domain.repository.TaskRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var taskRepository: TaskRepository
    private lateinit var quoteApiService: QuoteApiService
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        taskRepository = mockk(relaxed = true)
        quoteApiService = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state has loading quote`() = runTest {
        // Given
        coEvery { taskRepository.getActiveTasks() } returns flowOf(emptyList())
        coEvery { quoteApiService.getMotivationalQuote() } returns QuoteResponse(
            content = "Test quote",
            author = "Test Author",
            tags = listOf("education")
        )

        // When
        viewModel = DashboardViewModel(taskRepository, quoteApiService)

        // Then
        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
    }

    @Test
    fun `uiState updates with tasks from repository`() = runTest {
        // Given
        val tasks = listOf(
            Task(
                id = 1,
                title = "Task 1",
                courseCode = "CP3406",
                courseName = "Mobile Dev",
                dueDate = LocalDateTime.now(),
                priority = TaskPriority.HIGH,
                category = TaskCategory.ASSIGNMENT
            ),
            Task(
                id = 2,
                title = "Task 2",
                courseCode = "CP5307",
                courseName = "Advanced Mobile",
                dueDate = LocalDateTime.now(),
                priority = TaskPriority.MEDIUM,
                category = TaskCategory.QUIZ
            )
        )
        coEvery { taskRepository.getActiveTasks() } returns flowOf(tasks)
        coEvery { quoteApiService.getMotivationalQuote() } returns QuoteResponse(
            content = "Test quote",
            author = "Test Author",
            tags = listOf("education")
        )

        // When
        viewModel = DashboardViewModel(taskRepository, quoteApiService)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertThat(state.tasks).hasSize(2)
        assertThat(state.tasks[0].title).isEqualTo("Task 1")
    }

    @Test
    fun `quote loads successfully from API`() = runTest {
        // Given
        val expectedQuote = QuoteResponse(
            content = "Success is not final, failure is not fatal",
            author = "Winston Churchill",
            tags = listOf("success")
        )
        coEvery { taskRepository.getActiveTasks() } returns flowOf(emptyList())
        coEvery { quoteApiService.getMotivationalQuote() } returns expectedQuote

        // When
        viewModel = DashboardViewModel(taskRepository, quoteApiService)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertThat(state.quote).isEqualTo(expectedQuote.content)
        assertThat(state.quoteAuthor).isEqualTo(expectedQuote.author)
    }

    @Test
    fun `quote falls back to default on API error`() = runTest {
        // Given
        coEvery { taskRepository.getActiveTasks() } returns flowOf(emptyList())
        coEvery { quoteApiService.getMotivationalQuote() } throws Exception("Network error")

        // When
        viewModel = DashboardViewModel(taskRepository, quoteApiService)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertThat(state.quote).contains("The more that you read")
        assertThat(state.quoteAuthor).isEqualTo("Dr. Seuss")
    }

    @Test
    fun `refreshQuote loads new quote`() = runTest {
        // Given
        val initialQuote = QuoteResponse("Initial quote", "Author 1", listOf())
        val newQuote = QuoteResponse("New quote", "Author 2", listOf())
        coEvery { taskRepository.getActiveTasks() } returns flowOf(emptyList())
        coEvery { quoteApiService.getMotivationalQuote() } returnsMany listOf(initialQuote, newQuote)

        viewModel = DashboardViewModel(taskRepository, quoteApiService)
        advanceUntilIdle()

        // When
        viewModel.refreshQuote()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertThat(state.quote).isEqualTo("New quote")
    }
}
