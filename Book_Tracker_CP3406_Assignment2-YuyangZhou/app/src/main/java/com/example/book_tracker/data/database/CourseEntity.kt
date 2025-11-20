package com.example.book_tracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.book_tracker.domain.model.Course

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val code: String,
    val name: String,
    val instructor: String,
    val room: String,
    val color: String,
    val semester: String,
    val grade: String,
    val completedTasks: Int,
    val totalTasks: Int
)

fun CourseEntity.toDomain(): Course = Course(
    id = id,
    code = code,
    name = name,
    instructor = instructor,
    room = room,
    color = color,
    semester = semester,
    grade = grade,
    completedTasks = completedTasks,
    totalTasks = totalTasks
)

fun Course.toEntity(): CourseEntity = CourseEntity(
    id = id,
    code = code,
    name = name,
    instructor = instructor,
    room = room,
    color = color,
    semester = semester,
    grade = grade,
    completedTasks = completedTasks,
    totalTasks = totalTasks
)
