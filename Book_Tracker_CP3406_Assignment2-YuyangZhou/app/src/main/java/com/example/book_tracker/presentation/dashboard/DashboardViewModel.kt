package com.example.book_tracker.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_tracker.data.network.QuoteApiService
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val tasks: List<Task> = emptyList(),
    val dueToday: Int = 0,
    val plannedStudyHours: Double = 2.5,
    val weekProgress: Int = 87,
    val quote: String = "Loading...",
    val quoteAuthor: String = "",
    val isLoading: Boolean = false
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val quoteApiService: QuoteApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
        loadMotivationalQuote()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            taskRepository.getActiveTasks()
                .collect { tasks ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            tasks = tasks.take(5), // Show top 5 urgent tasks
                            dueToday = tasks.count { it.dueDate.toLocalDate() == java.time.LocalDate.now() }
                        )
                    }
                }
        }
    }

    private fun loadMotivationalQuote() {
        viewModelScope.launch {
            try {
                val quote = quoteApiService.getMotivationalQuote()
                _uiState.update { it.copy(
                    quote = quote.content,
                    quoteAuthor = quote.author
                )}
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    quote = "The more that you read, the more things you will know.",
                    quoteAuthor = "Dr. Seuss"
                )}
            }
        }
    }

    fun refreshQuote() {
        loadMotivationalQuote()
    }
}
