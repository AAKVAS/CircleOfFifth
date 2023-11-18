package com.example.circleoffifth.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circleoffifth.data.ChordRepository
import com.example.circleoffifth.data.entities.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val chordRepository: ChordRepository
) : ViewModel() {

    private val _modesCreated: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val modesCreated = _modesCreated.asStateFlow()

    init {
        viewModelScope.launch {
            val challengeFlow = chordRepository.getGameModeFlowByName(Mode.CHALLENGE)
            val surviveFlow = chordRepository.getGameModeFlowByName(Mode.SURVIVE)
            challengeFlow.combine(surviveFlow, ::bothModesExists).collect {
                _modesCreated.value = it
            }
        }
    }

    private fun bothModesExists(challenge: Mode?, survive: Mode?) : Boolean =
        challenge != null && survive != null
}