package com.example.book_tracker.di

import com.example.book_tracker.data.repository.CourseRepositoryImpl
import com.example.book_tracker.data.repository.TaskRepositoryImpl
import com.example.book_tracker.domain.repository.CourseRepository
import com.example.book_tracker.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    @Singleton
    abstract fun bindCourseRepository(
        courseRepositoryImpl: CourseRepositoryImpl
    ): CourseRepository
}
