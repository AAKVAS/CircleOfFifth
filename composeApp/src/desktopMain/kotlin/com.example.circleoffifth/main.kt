package com.example.circleoffifth

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.circleoffifth.di.appModule
import com.example.circleoffifth.ui.CircleOfFifthApp
import com.example.circleoffifth.ui.theme.AppTheme
import com.example.circleoffifth.ui.theme.LocalColorProvider
import org.koin.compose.KoinApplication

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Circle of fifth",
    ) {
        KoinApplication(
            application = {
                modules(appModule())
            }
        ) {
            AppTheme(isSystemInDarkTheme()) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LocalColorProvider.current.background
                ) {
                    CircleOfFifthApp()
                }
            }
        }
    }
}