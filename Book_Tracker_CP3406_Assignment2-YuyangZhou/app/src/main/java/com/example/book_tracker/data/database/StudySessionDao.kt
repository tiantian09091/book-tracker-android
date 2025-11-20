package com.example.book_tracker.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudySessionDao {
    @Query("SELECT * FROM study_sessions ORDER BY startTime DESC")
    fun getAllSessions(): Flow<List<StudySessionEntity>>

    @Query("SELECT * FROM study_sessions WHERE id = :id")
    suspend fun getSessionById(id: Long): StudySessionEntity?

    @Query("SELECT * FROM study_sessions WHERE courseCode = :courseCode ORDER BY startTime DESC")
    fun getSessionsByCourse(courseCode: String): Flow<List<StudySessionEntity>>

    @Query("SELECT * FROM study_sessions WHERE endTime IS NULL ORDER BY startTime DESC LIMIT 1")
    suspend fun getActiveSession(): StudySessionEntity?

    @Query("SELECT SUM(durationMinutes) FROM study_sessions WHERE startTime >= :startDate")
    suspend fun getTotalStudyTimeMinutes(startDate: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: StudySessionEntity): Long

    @Update
    suspend fun updateSession(session: StudySessionEntity)

    @Delete
    suspend fun deleteSession(session: StudySessionEntity)
}
