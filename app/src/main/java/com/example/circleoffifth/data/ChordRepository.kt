package com.example.circleoffifth.data

import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.ScoreState

interface ChordRepository {
    suspend fun updateSurviveRecord(record: Int)
    suspend fun updateChallengeRecord(record: Int)
    suspend fun getSurviveRecord(): Int
    suspend fun getChallengeRecord(): Int
    suspend fun getSurviveScoreState(): ScoreState?
    suspend fun getChallengeScoreState(): ScoreState?
    suspend fun saveScoreState(scoreState: ScoreState)
    suspend fun deleteSurviveScoreState()
    suspend fun deleteChallengeScoreState()
    suspend fun getGameModeByName(modeName: String): Mode?
}