package com.example.circleoffifth.data

import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.Records
import com.example.circleoffifth.data.entities.ScoreState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChordRepositoryImpl @Inject constructor(
    private val gameDao: GameDao
) : ChordRepository {
    override suspend fun getGameModeByName(modeName: String): Mode {
        return gameDao.getGameModeByName(modeName)
    }

    override fun getGameModeFlowByName(modeName: String): Flow<Mode?> {
        return gameDao.getGameModeFlowByName(modeName)
    }

    override suspend fun updateSurviveRecord(record: Int) {
        val mode: Mode = getGameModeByName(Mode.SURVIVE)
        updateRecord(mode.uid, record)
    }

    override suspend fun updateChallengeRecord(record: Int) {
        val mode: Mode = getGameModeByName(Mode.CHALLENGE)
        updateRecord(mode.uid, record)
    }

    private suspend fun updateRecord(modeUid: String, record: Int) {
        val recordEntity = Records(
            modeUid = modeUid,
            record = record
        )
        gameDao.saveRecord(recordEntity)
    }

    override suspend fun getSurviveRecord(): Int {
        val mode: Mode = getGameModeByName(Mode.SURVIVE)
        return gameDao.getRecords(mode.uid)?.record ?: 0
    }

    override suspend fun getChallengeRecord(): Int {
        val mode: Mode = getGameModeByName(Mode.CHALLENGE)
        return gameDao.getRecords(mode.uid)?.record ?: 0
    }

    override suspend fun getSurviveScoreState(): ScoreState? {
        val mode: Mode = getGameModeByName(Mode.SURVIVE)
        return gameDao.getGameScoreState(mode.uid)
    }

    override suspend fun getChallengeScoreState(): ScoreState? {
        val mode: Mode = getGameModeByName(Mode.CHALLENGE)
        return gameDao.getGameScoreState(mode.uid)
    }

    override suspend fun saveScoreState(scoreState: ScoreState) {
        gameDao.saveScore(scoreState)
    }

    override suspend fun deleteSurviveScoreState() {
        val mode: Mode = getGameModeByName(Mode.SURVIVE)
        gameDao.deleteScoreState(mode.uid)
    }

    override suspend fun deleteChallengeScoreState() {
        val mode: Mode = getGameModeByName(Mode.CHALLENGE)
        gameDao.deleteScoreState(mode.uid)
    }
}