package com.example.circleoffifth

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import circleoffifth.composeapp.generated.resources.Res
import circleoffifth.composeapp.generated.resources.app_name
import circleoffifth.composeapp.generated.resources.ic_launcher
import com.example.circleoffifth.di.appModule
import com.example.circleoffifth.ui.CircleOfFifthApp
import com.example.circleoffifth.ui.theme.AppTheme
import com.example.circleoffifth.ui.theme.LocalColorProvider
import com.example.circleoffifth.utils.JVMChordSoundManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinApplication

fun main() = application {
    val windowSize = rememberWindowState()
    val isPortraitOrientation = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        JVMChordSoundManager.create()
    }

    Window(
        onCloseRequest = {
            JVMChordSoundManager.release()
            exitApplication()
        },
        title = stringResource(Res.string.app_name),
        icon = painterResource(Res.drawable.ic_launcher),
        state = windowSize
    ) {
        LaunchedEffect(windowSize) {
            snapshotFlow {
                windowSize.size
            }
            .onEach {
                isPortraitOrientation.value = windowSize.size.width < windowSize.size.height
            }.launchIn(this)
        }

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
                    CircleOfFifthApp(isPortraitOrientation = isPortraitOrientation.value)
                }
            }
        }
    }
}