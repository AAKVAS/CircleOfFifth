package com.example.circleoffifth.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import circleoffifth.composeapp.generated.resources.Res
import circleoffifth.composeapp.generated.resources.game_over
import circleoffifth.composeapp.generated.resources.game_over_score
import circleoffifth.composeapp.generated.resources.restart
import com.example.circleoffifth.data.Chord
import com.example.circleoffifth.ui.components.ChordButton
import com.example.circleoffifth.ui.components.CircleOfFifth
import com.example.circleoffifth.ui.components.Moves
import com.example.circleoffifth.ui.components.Record
import com.example.circleoffifth.ui.components.Score
import com.example.circleoffifth.ui.theme.LocalColorProvider
import com.example.circleoffifth.ui.theme.paddingMedium
import com.example.circleoffifth.ui.theme.paddingSmall
import com.example.circleoffifth.ui.viewModel.ChallengeViewModel
import com.example.circleoffifth.utils.getChordSoundPlayer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ChallengeScreen(
    isPortraitOrientation: Boolean,
    viewModel: ChallengeViewModel = koinViewModel(),
) {
    val currentChord by viewModel.currentChord.collectAsState()
    val currentMove by viewModel.currentMove.collectAsState()
    val endGame by viewModel.isEndGame.collectAsState()

    val score by viewModel.score.collectAsState()
    val record by viewModel.record.collectAsState()

    if (isPortraitOrientation) {
        VerticalChallengeScreen(
            viewModel = viewModel,
            currentChord = currentChord,
            currentMove = currentMove,
            score = score,
            record = record,
            endGame = endGame
        )
    } else {
        HorizontalChallengeScreen(
            viewModel = viewModel,
            currentChord = currentChord,
            currentMove = currentMove,
            score = score,
            record = record,
            endGame = endGame
        )
    }
}

@Composable
fun VerticalChallengeScreen(
    viewModel: ChallengeViewModel,
    currentChord: Chord,
    currentMove: Int,
    score: Int,
    record: Int,
    endGame: Boolean
) {
    Surface {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingSmall.margin)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                ChallengeGameStateLabels(
                    currentMove = currentMove,
                    moveCount = ChallengeViewModel.MOVES_COUNT,
                    score = score,
                    record = record
                )
                CircleOfFifth(
                    onChordClick = {
                        getChordSoundPlayer().playChord(it)
                        viewModel.checkChord(it)
                    }
                )
            }
            ChordButton(onClick = {
                getChordSoundPlayer().playChord(currentChord)
                viewModel.clickPlayChordBtn()
            })
        }
    }
    if (endGame) {
        RestartDialog(
            score,
            viewModel::restart
        )
    }
}

@Composable
fun HorizontalChallengeScreen(
    viewModel: ChallengeViewModel,
    currentChord: Chord,
    currentMove: Int,
    score: Int,
    record: Int,
    endGame: Boolean
) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingSmall.margin)
        ) {
            CircleOfFifth(
                modifier = Modifier.fillMaxHeight(),
                onChordClick = {
                    getChordSoundPlayer().playChord(it)
                    viewModel.checkChord(it)
                }
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxSize()
            ) {
                ChallengeGameStateLabels(
                    currentMove = currentMove,
                    moveCount = ChallengeViewModel.MOVES_COUNT,
                    score = score,
                    record = record
                )
                ChordButton(
                    onClick = {
                        getChordSoundPlayer().playChord(currentChord)
                        viewModel.clickPlayChordBtn()
                    }
                )
            }
        }
    }
    if (endGame) {
        RestartDialog(
            score,
            viewModel::restart
        )
    }
}

@Composable
fun ChallengeGameStateLabels(
    modifier: Modifier = Modifier,
    currentMove: Int = 0,
    moveCount: Int = 0,
    score: Int = 0,
    record: Int = 0
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Moves(currentTry = currentMove, triesCount = moveCount)
        Score(score = score)
        Record(record = record)
    }
}

@Composable
fun RestartDialog(
    score: Int = 0,
    onPosButtonClick: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(paddingMedium.margin),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(paddingMedium.margin)
            ) {
                Text(
                    text = stringResource(Res.string.game_over),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = stringResource(Res.string.game_over_score, score),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(paddingMedium.margin)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    onClick = onPosButtonClick,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = LocalColorProvider.current.primary,
                        backgroundColor = LocalColorProvider.current.primaryContainer
                    )
                ) {
                    Text(text = stringResource(Res.string.restart))
                }
            }
        }
    }
}
