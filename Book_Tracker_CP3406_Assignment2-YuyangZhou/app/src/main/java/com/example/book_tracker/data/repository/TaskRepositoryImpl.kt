package com.example.book_tracker.data.repository

import com.example.book_tracker.data.database.TaskDao
import com.example.book_tracker.data.database.toDomain
import com.example.book_tracker.data.database.toEntity
import com.example.book_tracker.domain.model.Task
import com.example.book_tracker.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> =
        taskDao.getAllTasks().map { entities -> entities.map { it.toDomain() } }

    override fun getActiveTasks(): Flow<List<Task>> =
        taskDao.getActiveTasks().map { entities -> entities.map { it.toDomain() } }

    override fun getCompletedTasks(): Flow<List<Task>> =
        taskDao.getCompletedTasks().map { entities -> entities.map { it.toDomain() } }

    override suspend fun getTaskById(id: Long): Task? =
        taskDao.getTaskById(id)?.toDomain()

    override fun getTasksByCourse(courseCode: String): Flow<List<Task>> =
        taskDao.getTasksByCourse(courseCode).map { entities -> entities.map { it.toDomain() } }

    override suspend fun insertTask(task: Task): Long =
        taskDao.insertTask(task.toEntity())

    override suspend fun updateTask(task: Task) =
        taskDao.updateTask(task.toEntity())

    override suspend fun deleteTask(task: Task) =
        taskDao.deleteTask(task.toEntity())

    override suspend fun deleteTaskById(id: Long) =
        taskDao.deleteTaskById(id)

    override fun getActiveTaskCount(): Flow<Int> =
        taskDao.getActiveTaskCount()
}
