package com.example.circleoffifth.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.components.CircleOfFifth
import com.example.circleoffifth.utils.ChordSoundManager

@Composable
fun TrainingScreen() {
    Surface{
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            val configuration = LocalConfiguration.current
            CircleOfFifth(
                modifier =
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    Modifier.fillMaxHeight()
                    else Modifier.fillMaxWidth()
                ,
                onChordClick = { ChordSoundManager.playChord(it) }
            )
        }
    }
}

@Preview
@Composable
fun TrainingScreenPreview() {
    TrainingScreen()
}