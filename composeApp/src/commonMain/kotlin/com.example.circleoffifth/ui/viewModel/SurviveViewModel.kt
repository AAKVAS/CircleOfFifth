package com.example.circleoffifth.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circleoffifth.data.Chord
import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.ScoreState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.max

class SurviveViewModel (
    private val chordRepository: ChordRepository
) : ViewModel() {
    private val _record: MutableStateFlow<Int> = MutableStateFlow(0)
    val record: StateFlow<Int> = _record.asStateFlow()

    private val _score: MutableStateFlow<Int> = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _currentChord: MutableStateFlow<Chord> = MutableStateFlow(getRandomChord())
    val currentChord: StateFlow<Chord> = _currentChord.asStateFlow()

    private val _isEndGame: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEndGame: StateFlow<Boolean> = _isEndGame.asStateFlow()

    private var playChordBtnClicked = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _record.value = chordRepository.getSurviveRecord()
            chordRepository.getSurviveScoreState()?.let {
                _score.value = it.score
            }
        }
    }

    private fun getRandomChord(): Chord =
        Chord.entries.shuffled().first()

    private fun setCurrentChordRandom() {
        _currentChord.value = getRandomChord()
        playChordBtnClicked = false
    }

    fun checkChord(chord: Chord) {
        if (playChordBtnClicked) {
            if (chord == currentChord.value) {
                incrementScore()
            } else {
                finishGame()
            }
            setCurrentChordRandom()
        }
    }

    private fun incrementScore() {
        _score.value += SCORE_INCREMENT
        viewModelScope.launch(Dispatchers.IO) {
            val scoreState = ScoreState(
                UUID.randomUUID().toString(),
                chordRepository.getGameModeByName(Mode.SURVIVE).uid,
                _score.value
            )
            chordRepository.saveScoreState(scoreState)
        }
    }

    private fun finishGame() {
        _isEndGame.value = true
    }

    fun restart() {
        _isEndGame.value = false
        _record.value = max(record.value, score.value)
        _score.value = 0
        viewModelScope.launch(Dispatchers.IO) {
            chordRepository.updateSurviveRecord(record.value)
            chordRepository.deleteSurviveScoreState()
        }
    }

    fun clickPlayChordBtn() {
        playChordBtnClicked = true
    }

    companion object {
        const val SCORE_INCREMENT = 10
    }
}