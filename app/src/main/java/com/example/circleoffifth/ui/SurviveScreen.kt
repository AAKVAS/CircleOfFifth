package com.example.circleoffifth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.components.ChordButton
import com.example.circleoffifth.ui.components.CircleOfFifth
import com.example.circleoffifth.ui.components.Record
import com.example.circleoffifth.ui.components.Score
import com.example.circleoffifth.ui.components.Tries
import androidx.activity.viewModels
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.circleoffifth.ui.viewModel.SurviveViewModel
import com.example.circleoffifth.utils.ChordSoundManager

@Composable
fun SurviveScreen(
    viewModel: SurviveViewModel = hiltViewModel(),
) {
    val currentChord by remember { viewModel.currentChord }
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
                SurviveGameStateLabels(
                    score = score,
                    record = record
                )
                CircleOfFifth(onChordClick = {
                    ChordSoundManager.playChord(it)
                    viewModel.checkChord(it)
                })
            }
            val context = LocalContext.current

            ChordButton(onClick = {
                ChordSoundManager.playChord(currentChord)
                viewModel.clickPlayChordBtn()
            })
        }
    }
    if (endGame) {
        RestartDialog(viewModel::restart)
    }
}

@Composable
fun RestartDialog(
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


@Composable
fun SurviveGameStateLabels(
    score: Int = 0,
    record: Int = 0,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Score(score = score)
        Record(record = record)
    }
}

@Preview
@Composable
fun SurviveScreenPreview() {
    SurviveScreen()
}