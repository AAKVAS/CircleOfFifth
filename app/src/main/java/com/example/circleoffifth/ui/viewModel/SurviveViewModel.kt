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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class SurviveViewModel @Inject constructor(
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

    private var playChordBtnClicked = false

    init {
        viewModelScope.launch {
            _record.value = chordRepository.getSurviveRecord()
            chordRepository.getSurviveScoreState()?.let {
                _score.value = it.score
            }
        }
    }

    fun getRandomChord(): Chord =
        Chord.values().toList().shuffled().first()

    fun setCurrentChordRandom() {
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

    fun incrementScore() {
        _score.value += SCORE_INCREMENT
        viewModelScope.launch {
            val scoreState = ScoreState(
                UUID.randomUUID().toString(),
                chordRepository.getGameModeByName(Mode.SURVIVE)!!.uid,
                _score.value
            )
            chordRepository.saveScoreState(scoreState)
        }
    }

    fun finishGame() {
        _isEndGame.value = true
    }

    fun restart() {
        _isEndGame.value = false
        _record.value = max(record.value, score.value)
        _score.value = 0
        viewModelScope.launch {
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