package com.example.circleoffifth.ui.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun isPortraitOrientation(): Boolean {
    return LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
}