package com.example.circleoffifth.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circleoffifth.data.Chord
import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.entities.Mode
import com.example.circleoffifth.data.entities.ScoreState
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.max

class ChallengeViewModel(
    private val chordRepository: ChordRepository
) : ViewModel() {
    private val _record: MutableState<Int> = mutableIntStateOf(0)
    val record
        get() = _record

    private val _score: MutableState<Int> = mutableIntStateOf(0)

    val score
        get() = _score

    private val _currentChord: MutableState<Chord> = mutableStateOf(getRandomChord())
    val currentChord
        get() = _currentChord


    private val _isEndGame: MutableState<Boolean> = mutableStateOf(false)
    val isEndGame
        get() = _isEndGame

    private val _currentMove: MutableState<Int> = mutableIntStateOf(1)
    val currentMove
        get() = _currentMove

    private var playChordBtnClicked = false

    init {
        viewModelScope.launch {
            _record.value = chordRepository.getChallengeRecord()
            chordRepository.getChallengeScoreState()?.let {
                _score.value = it.score
                _currentMove.value = it.move
            }
        }
    }

    private fun getRandomChord(): Chord =
        Chord.values().toList().shuffled().first()

    private fun setCurrentChordRandom() {
        _currentChord.value = getRandomChord()
        playChordBtnClicked = false
    }

    fun checkChord(chord: Chord) {
        if (playChordBtnClicked) {
            if (chord == currentChord.value) {
                incrementScore()
            }
            setCurrentChordRandom()
            if (currentMove.value == MOVES_COUNT) {
                finishGame()
                return
            }
            _currentMove.value += 1
            viewModelScope.launch {
                val scoreState = ScoreState(
                    UUID.randomUUID().toString(),
                    chordRepository.getGameModeByName(Mode.CHALLENGE)!!.uid,
                    _score.value,
                    _currentMove.value
                )
                chordRepository.deleteChallengeScoreState()
                chordRepository.saveScoreState(scoreState)
            }
        }
    }

    private fun incrementScore() {
        _score.value += SCORE_INCREMENT
    }

    private fun finishGame() {
        _isEndGame.value = true
    }

    fun restart() {
        _isEndGame.value = false
        _record.value = max(record.value, score.value)
        _score.value = 0
        _currentMove.value = 0
        viewModelScope.launch {
            chordRepository.updateChallengeRecord(record.value)
            chordRepository.deleteChallengeScoreState()
        }
    }

    fun clickPlayChordBtn() {
        playChordBtnClicked = true
    }

    companion object {
        const val SCORE_INCREMENT = 10
        const val MOVES_COUNT = 10
    }
}