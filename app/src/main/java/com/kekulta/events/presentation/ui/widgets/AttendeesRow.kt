package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.widgets.base.rows.OverlappingRow
import com.kekulta.events.presentation.ui.theme.EventsTheme


@Composable
fun AttendeesRow(avatars: List<String?>, modifier: Modifier = Modifier, showAvatarsNum: Int = 5) {
    Box(
        modifier = modifier
            .padding(EventsTheme.sizes.sizeX2)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {
        if (avatars.isEmpty()) {
            Text(text = "Be the first!")
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OverlappingRow(overlappingPercentage = 0.35f) {
                    avatars.take(showAvatarsNum).forEach { avatar ->
                        UserSquareAvatar(
                            drawBorder = true,
                            url = avatar,
                        )
                    }
                }

                if (avatars.size > showAvatarsNum) {
                    Text(
                        modifier = Modifier.padding(start = EventsTheme.sizes.sizeX5),
                        text = "+${avatars.size - showAvatarsNum}",
                        style = EventsTheme.typography.bodyText1
                    )
                }
            }
        }
    }
}
