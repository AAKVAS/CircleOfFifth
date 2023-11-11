package com.example.circleoffifth.data

import com.example.circleoffifth.data.dao.GameDao
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.Records
import com.example.circleoffifth.data.entities.ScoreState
import java.util.UUID
import javax.inject.Inject

class ChordRepositoryImpl @Inject constructor(
    private val gameDao: GameDao
) : ChordRepository {
    override suspend fun updateSurviveRecord(record: Int) {
        val mode: Mode = gameDao.getGameModeByName(Mode.SURVIVE)
        updateRecord(mode.uid, record)
    }

    override suspend fun updateTrialRecord(record: Int) {
        val mode: Mode = gameDao.getGameModeByName(Mode.TRIAL)
        updateRecord(mode.uid, record)
    }

    private suspend fun updateRecord(modeUid: String, record: Int) {
        val recordEntity = Records(
            modeUid = modeUid,
            record = record)
        gameDao.saveRecord(recordEntity)
    }

    override suspend fun getSurviveRecord(): Int {
        val mode: Mode = gameDao.getGameModeByName(Mode.SURVIVE)
        return gameDao.getRecords(mode.uid)?.record ?: 0
    }

    override suspend fun getTrialRecord(): Int {
        val mode: Mode = gameDao.getGameModeByName(Mode.TRIAL)
        return gameDao.getRecords(mode.uid)?.record ?: 0
    }

    override suspend fun getSurviveScoreState(): ScoreState {
        val mode: Mode = gameDao.getGameModeByName(Mode.SURVIVE)
        return gameDao.getGameScoreState(mode.uid)
    }

    override suspend fun getTrialScoreState(): ScoreState {
        val mode: Mode = gameDao.getGameModeByName(Mode.TRIAL)
        return gameDao.getGameScoreState(mode.uid)
    }

    override suspend fun saveScoreState(scoreState: ScoreState) {
        gameDao.saveScore(scoreState)
    }

    override suspend fun deleteSurviveScoreState() {
        val mode: Mode = gameDao.getGameModeByName(Mode.SURVIVE)
        gameDao.deleteScoreState(mode.uid)
    }

    override suspend fun deleteTrialScoreState() {
        val mode: Mode = gameDao.getGameModeByName(Mode.TRIAL)
        gameDao.deleteScoreState(mode.uid)
    }
}