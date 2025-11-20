package com.example.book_tracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.model.TaskCategory
import com.example.book_tracker.domain.model.TaskPriority
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val courseCode: String,
    val courseName: String,
    val dueDate: String, // Store as ISO string
    val isCompleted: Boolean,
    val priority: String,
    val category: String,
    val createdAt: String,
    val updatedAt: String
)

fun TaskEntity.toDomain(): Task = Task(
    id = id,
    title = title,
    description = description,
    courseCode = courseCode,
    courseName = courseName,
    dueDate = LocalDateTime.parse(dueDate),
    isCompleted = isCompleted,
    priority = TaskPriority.valueOf(priority),
    category = TaskCategory.valueOf(category),
    createdAt = LocalDateTime.parse(createdAt),
    updatedAt = LocalDateTime.parse(updatedAt)
)

fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    courseCode = courseCode,
    courseName = courseName,
    dueDate = dueDate.toString(),
    isCompleted = isCompleted,
    priority = priority.name,
    category = category.name,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt.toString()
)
