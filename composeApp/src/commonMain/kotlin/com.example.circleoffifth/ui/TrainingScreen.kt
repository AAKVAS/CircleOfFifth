package com.example.circleoffifth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.circleoffifth.ui.components.CircleOfFifth
import com.example.circleoffifth.ui.theme.LocalColorProvider
import com.example.circleoffifth.ui.theme.paddingSmall
import com.example.circleoffifth.utils.getChordSoundPlayer

@Composable
fun TrainingScreen(
    isPortraitOrientation: Boolean
) {
    Surface(color = LocalColorProvider.current.background) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingSmall.margin)
        ) {
            CircleOfFifth(
                modifier =
                    if (isPortraitOrientation.not()) {
                        Modifier.fillMaxHeight()
                    } else  {
                        Modifier.fillMaxWidth()
                    },
                onChordClick = { getChordSoundPlayer().playChord(it) }
            )
        }
    }
}
