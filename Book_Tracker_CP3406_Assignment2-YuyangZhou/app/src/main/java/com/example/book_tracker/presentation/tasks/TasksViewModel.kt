package com.example.book_tracker.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.model.TaskPriority
import com.example.book_tracker.domain.model.TaskCategory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

enum class TaskFilter {
    ALL, DUE_TODAY, THIS_WEEK, OVERDUE, COMPLETED
}

data class TasksUiState(
    val tasks: List<Task> = emptyList(),
    val selectedFilter: TaskFilter = TaskFilter.ALL,
    val isLoading: Boolean = false
)

class TasksViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState: StateFlow<TasksUiState> = _uiState.asStateFlow()

    init {
        loadSampleTasks()
    }

    private fun loadSampleTasks() {
        // Sample data for demonstration
        val sampleTasks = listOf(
            Task(
                id = 1,
                title = "Complete React Native Quiz",
                courseCode = "CP5307",
                courseName = "Mobile Development",
                dueDate = LocalDateTime.now().plusHours(12),
                isCompleted = false,
                priority = TaskPriority.URGENT,
                category = TaskCategory.QUIZ
            ),
            Task(
                id = 2,
                title = "Database Design Assignment",
                courseCode = "CP3406",
                courseName = "Database Systems",
                dueDate = LocalDateTime.now().plusDays(2),
                isCompleted = false,
                priority = TaskPriority.HIGH,
                category = TaskCategory.ASSIGNMENT
            ),
            Task(
                id = 3,
                title = "Study for Data Structures Exam",
                courseCode = "CP3307",
                courseName = "Data Structures",
                dueDate = LocalDateTime.now().plusDays(5),
                isCompleted = false,
                priority = TaskPriority.URGENT,
                category = TaskCategory.EXAM
            ),
            Task(
                id = 4,
                title = "Complete Lab Report",
                courseCode = "CP5307",
                courseName = "Mobile Development",
                dueDate = LocalDateTime.now().minusHours(2),
                isCompleted = false,
                priority = TaskPriority.HIGH,
                category = TaskCategory.ASSIGNMENT
            ),
            Task(
                id = 5,
                title = "Read Chapter 5 - Algorithms",
                courseCode = "CP3307",
                courseName = "Data Structures",
                dueDate = LocalDateTime.now().plusDays(1),
                isCompleted = true,
                priority = TaskPriority.MEDIUM,
                category = TaskCategory.ASSIGNMENT
            ),
            Task(
                id = 6,
                title = "Group Project Meeting",
                courseCode = "CP3406",
                courseName = "Database Systems",
                dueDate = LocalDateTime.now().plusHours(6),
                isCompleted = false,
                priority = TaskPriority.MEDIUM,
                category = TaskCategory.PROJECT
            ),
            Task(
                id = 7,
                title = "Submit Weekly Journal",
                courseCode = "CP5307",
                courseName = "Mobile Development",
                dueDate = LocalDateTime.now().plusDays(3),
                isCompleted = true,
                priority = TaskPriority.LOW,
                category = TaskCategory.ASSIGNMENT
            )
        )

        _uiState.update { it.copy(tasks = sampleTasks) }
    }

    fun selectFilter(filter: TaskFilter) {
        _uiState.update { it.copy(selectedFilter = filter) }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isCompleted = !task.isCompleted)
            val updatedTasks = _uiState.value.tasks.map {
                if (it.id == task.id) updatedTask else it
            }
            _uiState.update { it.copy(tasks = updatedTasks) }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            val updatedTasks = _uiState.value.tasks.filter { it.id != task.id }
            _uiState.update { it.copy(tasks = updatedTasks) }
        }
    }
}
