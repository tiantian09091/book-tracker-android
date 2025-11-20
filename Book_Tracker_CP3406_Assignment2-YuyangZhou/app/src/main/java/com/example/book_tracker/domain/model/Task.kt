package com.example.book_tracker.domain.model

import java.time.LocalDateTime

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val courseCode: String,
    val courseName: String,
    val dueDate: LocalDateTime,
    val isCompleted: Boolean = false,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val category: TaskCategory = TaskCategory.ASSIGNMENT,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class TaskPriority {
    LOW, MEDIUM, HIGH, URGENT
}

enum class TaskCategory {
    ASSIGNMENT, QUIZ, EXAM, READING, PROJECT, OTHER
}
