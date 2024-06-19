package com.kekulta.events.ui.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun RoundChip(text: String) {
    Text(
        modifier = Modifier
            .padding(2.dp)
            .background(
                EventsTheme.colors.brandBackground,
                shape = RoundedCornerShape(40.dp)
            )
            .padding(PaddingValues(horizontal = 12.dp, vertical = 8.dp)),
        text = text,
        style = EventsTheme.typography.metadata3,
        color = EventsTheme.colors.brandDark
    )
}
