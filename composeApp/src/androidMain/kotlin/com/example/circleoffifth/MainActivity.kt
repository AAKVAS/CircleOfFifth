package com.example.circleoffifth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.circleoffifth.di.appModule
import com.example.circleoffifth.ui.CircleOfFifthApp
import com.example.circleoffifth.ui.theme.CommonTheme
import org.koin.compose.KoinApplication


class MainActivity : ComponentActivity() {

    // TODO splash Screen while modes creating
    //private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            KoinApplication(
                application = {
                    modules(appModule())
                }
            ) {
                CircleOfFifthTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = CommonTheme.background
                    ) {
                        CircleOfFifthApp()
                    }
                }
            }
        }

    }
}