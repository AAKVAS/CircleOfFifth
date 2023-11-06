package com.example.circleoffifth.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChordButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(onClick = onClick) {
        Text(text = "Play chord")
    }
}