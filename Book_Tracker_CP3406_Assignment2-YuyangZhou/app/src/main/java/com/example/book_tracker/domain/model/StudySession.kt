package com.example.book_tracker.domain.model

import java.time.LocalDateTime

data class StudySession(
    val id: Long = 0,
    val courseCode: String,
    val courseName: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime?,
    val durationMinutes: Int = 0,
    val notes: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now()
)
