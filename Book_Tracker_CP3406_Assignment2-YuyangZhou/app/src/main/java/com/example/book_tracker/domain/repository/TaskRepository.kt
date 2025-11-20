package com.example.book_tracker.domain.repository

import com.example.book_tracker.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun getActiveTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    fun getTasksByCourse(courseCode: String): Flow<List<Task>>
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteTaskById(id: Long)
    fun getActiveTaskCount(): Flow<Int>
}
