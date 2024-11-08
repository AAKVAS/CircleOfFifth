package com.example.circleoffifth.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.Records
import com.example.circleoffifth.data.entities.ScoreState
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM records WHERE mode_uid = :modeUid")
    suspend fun getRecords(modeUid: String): Records?

    @Query("SELECT * FROM mode WHERE name = :name")
    suspend fun getGameModeByName(name: String): Mode?

    @Query("SELECT * FROM mode WHERE name = :name")
    fun getGameModeFlowByName(name: String): Flow<Mode?>

    @Query("SELECT * FROM score_state WHERE mode_uid = :modeUid")
    suspend fun getGameScoreState(modeUid: String): ScoreState?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecord(record: Records)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveScore(score: ScoreState)

    @Query("DELETE FROM score_state WHERE mode_uid = :modeUid")
    suspend fun deleteScoreState(modeUid: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMode(mode: Mode)
}