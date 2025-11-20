package com.example.book_tracker.domain.model

import java.time.LocalDateTime

data class CalendarEvent(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val location: String = "",
    val courseCode: String = "",
    val type: EventType = EventType.OTHER,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class EventType {
    LECTURE, LAB, TUTORIAL, STUDY_SESSION, EXAM, MEETING, OTHER
}
