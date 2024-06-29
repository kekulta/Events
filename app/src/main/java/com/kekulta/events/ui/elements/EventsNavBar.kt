package com.kekulta.events.ui.elements

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.base.modifiers.advancedShadow
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun EventsNavBar() {
    NavigationBar(
        modifier = Modifier.advancedShadow(
            color = Color(0x0A00000A),
            borderRadius = 0.dp,
            blurRadius = 24.dp,
            offsetY = (-1).dp,
            spread = 0f,
        ),
        containerColor = EventsTheme.colors.neutralWhite
    ) {}
}
