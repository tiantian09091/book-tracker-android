package com.example.book_tracker.di

import android.content.Context
import androidx.room.Room
import com.example.book_tracker.data.database.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): StudyTrackerDatabase {
        return Room.databaseBuilder(
            context,
            StudyTrackerDatabase::class.java,
            StudyTrackerDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTaskDao(database: StudyTrackerDatabase): TaskDao = database.taskDao()

    @Provides
    fun provideCourseDao(database: StudyTrackerDatabase): CourseDao = database.courseDao()

    @Provides
    fun provideStudySessionDao(database: StudyTrackerDatabase): StudySessionDao = database.studySessionDao()

    @Provides
    fun provideCalendarEventDao(database: StudyTrackerDatabase): CalendarEventDao = database.calendarEventDao()
}
