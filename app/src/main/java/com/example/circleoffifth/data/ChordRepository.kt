package com.example.circleoffifth.data

import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.ScoreState

interface ChordRepository {
    suspend fun updateSurviveRecord(record: Int)
    suspend fun updateTrialRecord(record: Int)
    suspend fun getSurviveRecord(): Int
    suspend fun getTrialRecord(): Int
    suspend fun getSurviveScoreState(): ScoreState?
    suspend fun getTrialScoreState(): ScoreState?
    suspend fun saveScoreState(scoreState: ScoreState)
    suspend fun deleteSurviveScoreState()
    suspend fun deleteTrialScoreState()
    suspend fun getGameModeByName(modeName: String): Mode
}