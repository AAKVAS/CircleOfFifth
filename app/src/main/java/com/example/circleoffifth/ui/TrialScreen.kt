package com.example.circleoffifth.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.components.ChordButton
import com.example.circleoffifth.ui.components.CircleOfFifth
import com.example.circleoffifth.ui.components.Record
import com.example.circleoffifth.ui.components.Score
import com.example.circleoffifth.ui.components.Tries

@Composable
fun TrialScreen() {
    val context = LocalContext.current
    Surface {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                TrialGameStateLabels()
                CircleOfFifth(onChordClick = { Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show() })
            }
            ChordButton()
        }
    }
}

@Composable
fun TrialGameStateLabels(
    modifier: Modifier = Modifier,
    currentTry: Int = 0,
    triesCount: Int = 0,
    score: Int = 0,
    record: Int = 0
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Tries(currentTry = currentTry, triesCount = triesCount)
        Score(score = score)
        Record(record = record)
    }
}

@Preview
@Composable
fun TrialScreenPreview() {
    TrialScreen()
}