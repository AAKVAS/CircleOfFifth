package com.example.circleoffifth.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.circleoffifth.R

@Composable
fun Tries(
    currentTry: Int,
    triesCount: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.tries, currentTry, triesCount),
        modifier = modifier
    )
}

@Composable
fun Score(
    score: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.score, score),
        modifier = modifier
    )
}

@Composable
fun Record(
    record: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.record, record),
        modifier = modifier
    )
}


