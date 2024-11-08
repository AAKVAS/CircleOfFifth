package com.example.circleoffifth.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import circleoffifth.composeapp.generated.resources.Res
import circleoffifth.composeapp.generated.resources.moves
import circleoffifth.composeapp.generated.resources.record
import circleoffifth.composeapp.generated.resources.score
import org.jetbrains.compose.resources.stringResource

@Composable
fun Moves(
    currentTry: Int,
    triesCount: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(Res.string.moves, currentTry, triesCount),
        modifier = modifier
    )
}

@Composable
fun Score(
    score: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(Res.string.score, score),
        modifier = modifier
    )
}

@Composable
fun Record(
    record: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(Res.string.record, record),
        modifier = modifier
    )
}


