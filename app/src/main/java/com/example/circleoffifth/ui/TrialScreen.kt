package com.example.circleoffifth.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.components.ChordButton
import com.example.circleoffifth.ui.components.CircleOfFifth
import com.example.circleoffifth.ui.components.Record
import com.example.circleoffifth.ui.components.Score
import com.example.circleoffifth.ui.components.Tries
import com.example.circleoffifth.ui.viewModel.TrialViewModel
import com.example.circleoffifth.utils.ChordSoundManager

@Composable
fun TrialScreen(
    viewModel: TrialViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentChord by remember { viewModel.currentChord }
    val currentMove by remember { viewModel.currentMove }
    val endGame by remember { viewModel.isEndGame }

    val score by remember { viewModel.score }
    val record by remember { viewModel.record }

    Surface {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                TrialGameStateLabels(
                    currentMove = currentMove,
                    moveCount = TrialViewModel.MOVES_COUNT,
                    score = score,
                    record = record
                )
                CircleOfFifth(onChordClick = {
                    ChordSoundManager.playChord(it)
                    viewModel.checkChord(it)
                })
            }
            ChordButton(onClick = {
                ChordSoundManager.playChord(currentChord)
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
fun TrialGameStateLabels(
    modifier: Modifier = Modifier,
    currentMove: Int = 0,
    moveCount: Int = 0,
    score: Int = 0,
    record: Int = 0
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Tries(currentTry = currentMove, triesCount = moveCount)
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
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(id = R.string.game_over),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(id = R.string.game_over_score, score),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    onClick = onPosButtonClick
                ) {
                    Text(text = stringResource(id = R.string.restart))
                }
            }
        }
    }
}

@Preview
@Composable
fun TrialScreenPreview() {
    TrialScreen()
}