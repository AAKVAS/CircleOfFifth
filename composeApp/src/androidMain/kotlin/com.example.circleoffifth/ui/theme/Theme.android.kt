package com.example.circleoffifth.ui.theme

actual fun getColorTheme(
    useDarkTheme: Boolean
): ColorTheme {
    return if (!useDarkTheme) {
        lightColorTheme
    } else {
        darkColorTheme
    }
}