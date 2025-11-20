package com.example.book_tracker.domain.model

data class Course(
    val id: Long = 0,
    val code: String,
    val name: String,
    val instructor: String = "",
    val room: String = "",
    val color: String = "#3B82F6", // Default blue
    val semester: String = "",
    val grade: String = "",
    val completedTasks: Int = 0,
    val totalTasks: Int = 0
)
