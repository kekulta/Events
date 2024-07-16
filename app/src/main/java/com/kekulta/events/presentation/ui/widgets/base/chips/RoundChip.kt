package com.kekulta.events.presentation.ui.widgets.base.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.theme.EventsTheme

@Composable
fun RoundChip(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(EventsTheme.sizes.sizeX10)
            .background(
                EventsTheme.colors.brandBackground,
                shape = RoundedCornerShape(EventsTheme.sizes.sizeX20)
            )
            .padding(
                PaddingValues(
                    horizontal = EventsTheme.sizes.sizeX4, vertical = EventsTheme.sizes.sizeX1
                )
            ),
    ) {
        Text(
            text = text,
            style = EventsTheme.typography.metadata3,
            color = EventsTheme.colors.brandDark
        )
    }
}
