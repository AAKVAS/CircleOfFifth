package com.example.circleoffifth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.circleoffifth.ui.CircleOfFifthApp
import com.example.circleoffifth.ui.theme.CircleOfFifthTheme
import com.example.circleoffifth.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.modesCreated.value
            }
        }
        super.onCreate(savedInstanceState)

        setContent {
            CircleOfFifthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircleOfFifthApp()
                }
            }
        }

    }
}