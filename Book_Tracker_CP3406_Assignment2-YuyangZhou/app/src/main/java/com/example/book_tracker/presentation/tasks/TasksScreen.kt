package com.example.book_tracker.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.model.TaskPriority
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tasks",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            FloatingActionButton(
                onClick = { /* Navigate to add task */ },
                modifier = Modifier.size(48.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }

        // Filter chips
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(TaskFilter.values()) { filter ->
                FilterChip(
                    modifier = Modifier.testTag("filter_chip_${filter.name}"),
                    selected = uiState.selectedFilter == filter,
                    onClick = { viewModel.selectFilter(filter) },
                    label = {
                        Text(
                            text = when (filter) {
                                TaskFilter.ALL -> "All"
                                TaskFilter.DUE_TODAY -> "Due Today"
                                TaskFilter.THIS_WEEK -> "This Week"
                                TaskFilter.OVERDUE -> "Overdue"
                                TaskFilter.COMPLETED -> "Completed"
                            }
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tasks list
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val filteredTasks = filterTasks(uiState.tasks, uiState.selectedFilter)

            if (filteredTasks.isEmpty()) {
                item {
                    EmptyTasksView(filter = uiState.selectedFilter)
                }
            } else {
                items(filteredTasks) { task ->
                    TaskCard(
                        task = task,
                        onToggleComplete = { viewModel.toggleTaskCompletion(task) },
                        onClick = { /* Navigate to task detail */ }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: () -> Unit,
    onClick: () -> Unit
) {
    val borderColor = when {
        task.isCompleted -> Color(0xFF10B981)
        task.priority == TaskPriority.URGENT -> Color(0xFFEF4444)
        task.priority == TaskPriority.HIGH -> Color(0xFFF59E0B)
        else -> Color(0xFF3B82F6)
    }

    val timeUntilDue = ChronoUnit.HOURS.between(LocalDateTime.now(), task.dueDate)
    val dueText = when {
        task.isCompleted -> "Completed"
        timeUntilDue < 0 -> "Overdue"
        timeUntilDue < 24 -> "Due in ${timeUntilDue}h"
        else -> "Due ${task.dueDate.format(DateTimeFormatter.ofPattern("MMM dd"))}"
    }

    val dueColor = when {
        task.isCompleted -> Color(0xFF10B981)
        timeUntilDue < 0 -> Color(0xFFEF4444)
        timeUntilDue < 24 -> Color(0xFFEF4444)
        timeUntilDue < 48 -> Color(0xFFF59E0B)
        else -> Color(0xFF3B82F6)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = borderColor.copy(alpha = 0.1f)
                )
                .padding(start = 4.dp)
        ) {
            // Left border
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(borderColor)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Checkbox + Content
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Checkbox
                    IconButton(
                        onClick = onToggleComplete,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (task.isCompleted)
                                Icons.Default.CheckCircle
                            else
                                Icons.Default.Circle,
                            contentDescription = if (task.isCompleted) "Completed" else "Mark complete",
                            tint = borderColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Task info
                    Column {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Medium,
                            textDecoration = if (task.isCompleted)
                                TextDecoration.LineThrough
                            else
                                TextDecoration.None
                        )
                        Text(
                            text = "${task.courseCode} - ${task.courseName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = dueText,
                            style = MaterialTheme.typography.bodySmall,
                            color = dueColor,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Star icon for important tasks
                if (task.priority == TaskPriority.URGENT || task.priority == TaskPriority.HIGH) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "High priority",
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyTasksView(filter: TaskFilter) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = when (filter) {
                TaskFilter.ALL -> "No tasks yet"
                TaskFilter.DUE_TODAY -> "No tasks due today"
                TaskFilter.THIS_WEEK -> "No tasks this week"
                TaskFilter.OVERDUE -> "No overdue tasks"
                TaskFilter.COMPLETED -> "No completed tasks"
            },
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Tap + to add a new task",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun filterTasks(tasks: List<Task>, filter: TaskFilter): List<Task> {
    val now = LocalDateTime.now()
    val today = now.toLocalDate()
    val endOfWeek = today.plusDays(7)

    return when (filter) {
        TaskFilter.ALL -> tasks.filter { !it.isCompleted }
        TaskFilter.DUE_TODAY -> tasks.filter {
            !it.isCompleted && it.dueDate.toLocalDate() == today
        }
        TaskFilter.THIS_WEEK -> tasks.filter {
            !it.isCompleted && it.dueDate.toLocalDate() <= endOfWeek
        }
        TaskFilter.OVERDUE -> tasks.filter {
            !it.isCompleted && it.dueDate.isBefore(now)
        }
        TaskFilter.COMPLETED -> tasks.filter { it.isCompleted }
    }.sortedBy { it.dueDate }
}
