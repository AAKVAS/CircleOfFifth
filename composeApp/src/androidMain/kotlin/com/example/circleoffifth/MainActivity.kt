package com.example.circleoffifth

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.circleoffifth.ui.CircleOfFifthApp
import com.example.circleoffifth.ui.theme.AppTheme
import com.example.circleoffifth.ui.theme.LocalColorProvider


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(isSystemInDarkTheme()) {
                val isPortraitOrientation =
                    LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LocalColorProvider.current.background
                ) {
                    CircleOfFifthApp(isPortraitOrientation = isPortraitOrientation)
                }
            }
        }
    }
}