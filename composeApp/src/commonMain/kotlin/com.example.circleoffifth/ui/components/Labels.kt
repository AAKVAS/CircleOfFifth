package com.example.circleoffifth.ui.components


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import circleoffifth.composeapp.generated.resources.Res
import circleoffifth.composeapp.generated.resources.moves
import circleoffifth.composeapp.generated.resources.record
import circleoffifth.composeapp.generated.resources.score
import com.example.circleoffifth.ui.theme.LocalColorProvider
import org.jetbrains.compose.resources.stringResource

@Composable
fun Moves(
    currentTry: Int,
    triesCount: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(Res.string.moves, currentTry, triesCount),
        color = LocalColorProvider.current.onBackground,
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
        color = LocalColorProvider.current.onBackground,
        modifier = modifier
    )
}

@Composable
fun Record(
    record: Int
) {
    Text(
        text = stringResource(Res.string.record, record),
        color = LocalColorProvider.current.onBackground,
    )
}


