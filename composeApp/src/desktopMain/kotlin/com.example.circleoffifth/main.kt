package com.example.circleoffifth

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.circleoffifth.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Circle of fifth",
    ) {
        App()
    }
}