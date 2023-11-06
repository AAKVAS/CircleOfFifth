package com.example.circleoffifth.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.circleoffifth.R
import com.example.circleoffifth.ui.components.CircleOfFifth

@Composable
fun TrainingScreen() {
    Surface{
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            val context = LocalContext.current
            CircleOfFifth(
                onChordClick = { Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show() },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Preview
@Composable
fun TrainingScreenPreview() {
    TrainingScreen()
}