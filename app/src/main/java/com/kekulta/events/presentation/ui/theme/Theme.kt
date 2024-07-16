package com.kekulta.events.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

const val scaleFactor = 1.25

@Composable
fun EventsTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides LightColorScheme,
        LocalTypography provides EventsTypographyValue,
        LocalSizeSystem provides EventsLocalSizeSystem,
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object EventsTheme {
    val colors: EventsColorScheme
        @Composable get() = LocalColorScheme.current
    val typography: EventsTypography
        @Composable get() = LocalTypography.current
    val sizes: EventsSizeSystem
        @Composable get() = LocalSizeSystem.current
}