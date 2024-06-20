package com.kekulta.events.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun EventsTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides LightColorScheme,
        LocalEventsTypography provides EventsTypographyValue,
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
        @Composable get() = LocalEventsTypography.current
}