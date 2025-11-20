package com.example.book_tracker.data.repository

import com.example.book_tracker.data.database.CourseDao
import com.example.book_tracker.data.database.toDomain
import com.example.book_tracker.data.database.toEntity
import com.example.book_tracker.domain.model.Course
import com.example.book_tracker.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao
) : CourseRepository {

    override fun getAllCourses(): Flow<List<Course>> =
        courseDao.getAllCourses().map { entities -> entities.map { it.toDomain() } }

    override suspend fun getCourseById(id: Long): Course? =
        courseDao.getCourseById(id)?.toDomain()

    override suspend fun getCourseByCode(code: String): Course? =
        courseDao.getCourseByCode(code)?.toDomain()

    override suspend fun insertCourse(course: Course): Long =
        courseDao.insertCourse(course.toEntity())

    override suspend fun updateCourse(course: Course) =
        courseDao.updateCourse(course.toEntity())

    override suspend fun deleteCourse(course: Course) =
        courseDao.deleteCourse(course.toEntity())

    override fun getCourseCount(): Flow<Int> =
        courseDao.getCourseCount()
}
