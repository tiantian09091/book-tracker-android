package com.example.book_tracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.book_tracker.domain.model.StudySession
import java.time.LocalDateTime

@Entity(tableName = "study_sessions")
data class StudySessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val courseCode: String,
    val courseName: String,
    val startTime: String,
    val endTime: String?,
    val durationMinutes: Int,
    val notes: String,
    val createdAt: String
)

fun StudySessionEntity.toDomain(): StudySession = StudySession(
    id = id,
    courseCode = courseCode,
    courseName = courseName,
    startTime = LocalDateTime.parse(startTime),
    endTime = endTime?.let { LocalDateTime.parse(it) },
    durationMinutes = durationMinutes,
    notes = notes,
    createdAt = LocalDateTime.parse(createdAt)
)

fun StudySession.toEntity(): StudySessionEntity = StudySessionEntity(
    id = id,
    courseCode = courseCode,
    courseName = courseName,
    startTime = startTime.toString(),
    endTime = endTime?.toString(),
    durationMinutes = durationMinutes,
    notes = notes,
    createdAt = createdAt.toString()
)
