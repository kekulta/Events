package com.kekulta.events.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.base.rows.OverlappingRow
import com.kekulta.events.ui.theme.EventsTheme


@Composable
fun AttendeesRow(avatars: List<String?>, modifier: Modifier = Modifier, showAvatarsNum: Int = 5) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 48.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        if (avatars.isEmpty()) {
            Text(modifier = Modifier.padding(start = 24.dp), text = "Be the first!")
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
                        modifier = Modifier.padding(start = 14.dp),
                        text = "+${avatars.size - showAvatarsNum}",
                        style = EventsTheme.typography.bodyText1
                    )
                }
            }
        }
    }
}
