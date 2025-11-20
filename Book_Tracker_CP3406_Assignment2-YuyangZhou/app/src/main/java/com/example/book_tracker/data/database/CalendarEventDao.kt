package com.example.book_tracker.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarEventDao {
    @Query("SELECT * FROM calendar_events ORDER BY startTime ASC")
    fun getAllEvents(): Flow<List<CalendarEventEntity>>

    @Query("SELECT * FROM calendar_events WHERE id = :id")
    suspend fun getEventById(id: Long): CalendarEventEntity?

    @Query("SELECT * FROM calendar_events WHERE startTime >= :startDate AND startTime <= :endDate ORDER BY startTime ASC")
    fun getEventsBetween(startDate: String, endDate: String): Flow<List<CalendarEventEntity>>

    @Query("SELECT * FROM calendar_events WHERE courseCode = :courseCode ORDER BY startTime ASC")
    fun getEventsByCourse(courseCode: String): Flow<List<CalendarEventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: CalendarEventEntity): Long

    @Update
    suspend fun updateEvent(event: CalendarEventEntity)

    @Delete
    suspend fun deleteEvent(event: CalendarEventEntity)
}
