package com.example.book_tracker.domain.repository

import com.example.book_tracker.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    suspend fun getCourseById(id: Long): Course?
    suspend fun getCourseByCode(code: String): Course?
    suspend fun insertCourse(course: Course): Long
    suspend fun updateCourse(course: Course)
    suspend fun deleteCourse(course: Course)
    fun getCourseCount(): Flow<Int>
}
