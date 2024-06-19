package com.kekulta.events.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/* TODO: Finish color palette */
@Immutable
data class EventsColorScheme(
    val brandDark: Color,
    val brandDefault: Color,
    val brandLight: Color,
    val brandBackground: Color,
    val neutralActive: Color,
    val neutralBody: Color,
    val neutralWeak: Color,
    val neutralDisabled: Color,
    val neutralWhite: Color,
    val neutralLine: Color,
    val neutralOffWhite: Color,
)


val LightColorScheme = EventsColorScheme(
    brandDefault = Color(0xFF9A41FE),
    brandDark = Color(0xFF660EC8),
    brandBackground = Color(0xFFF5ECFF),
    brandLight = Color(0xFF9A41FE),
    neutralActive = Color(0xFF29183B),
    neutralBody = Color(0xFF1D0835),
    neutralWeak = Color(0xFFA4A4A4),
    neutralDisabled = Color(0xFFD4DBE7),
    neutralLine = Color(0xFFEDEDED),
    neutralWhite = Color(0xFFFFFFFF),
    neutralOffWhite = Color(0xFFF7F7FC),
)

val LocalColorScheme = staticCompositionLocalOf {
    LightColorScheme
}

