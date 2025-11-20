package com.example.book_tracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.book_tracker.domain.model.CalendarEvent
import com.example.book_tracker.domain.model.EventType
import java.time.LocalDateTime

@Entity(tableName = "calendar_events")
data class CalendarEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val location: String,
    val courseCode: String,
    val type: String,
    val createdAt: String
)

fun CalendarEventEntity.toDomain(): CalendarEvent = CalendarEvent(
    id = id,
    title = title,
    description = description,
    startTime = LocalDateTime.parse(startTime),
    endTime = LocalDateTime.parse(endTime),
    location = location,
    courseCode = courseCode,
    type = EventType.valueOf(type),
    createdAt = LocalDateTime.parse(createdAt)
)

fun CalendarEvent.toEntity(): CalendarEventEntity = CalendarEventEntity(
    id = id,
    title = title,
    description = description,
    startTime = startTime.toString(),
    endTime = endTime.toString(),
    location = location,
    courseCode = courseCode,
    type = type.name,
    createdAt = createdAt.toString()
)
