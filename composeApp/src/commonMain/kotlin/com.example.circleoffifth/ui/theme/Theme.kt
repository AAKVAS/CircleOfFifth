package com.example.circleoffifth.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorTheme(
    val primary: Color,
    val background: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onSurface: Color,
)

val lightColorTheme = ColorTheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    background = md_theme_light_background,
    onSurface = md_theme_light_onSurface,
)

val darkColorTheme = ColorTheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    background = md_theme_dark_background,
    onSurface = md_theme_dark_onSurface,
)

expect fun getColorTheme(
    useDarkTheme: Boolean,
): ColorTheme

val LocalColorProvider = staticCompositionLocalOf<ColorTheme> {
    error("No default implementation")
}

@Composable
fun AppTheme(
    useDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorProvider provides getColorTheme(useDarkTheme)
    ) {
        content.invoke()
    }
}
