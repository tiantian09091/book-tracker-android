package com.example.book_tracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        TaskEntity::class,
        CourseEntity::class,
        StudySessionEntity::class,
        CalendarEventEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StudyTrackerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun courseDao(): CourseDao
    abstract fun studySessionDao(): StudySessionDao
    abstract fun calendarEventDao(): CalendarEventDao

    companion object {
        const val DATABASE_NAME = "study_tracker_db"
    }
}
