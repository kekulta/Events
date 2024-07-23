package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import com.kekulta.events.presentation.ui.models.AttendeeVo
import com.kekulta.events.presentation.ui.theme.EventsTheme


@Composable
fun AttendeesRow(
    attendees: List<AttendeeVo>, modifier: Modifier = Modifier, showAvatarsNum: Int = 5
) {
    Box(
        modifier = modifier
            .padding(EventsTheme.sizes.sizeX2)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {
        if (attendees.isEmpty()) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Be the first!",
                style = EventsTheme.typography.subheading2
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(-EventsTheme.sizes.sizeX9)
                ) {
                    itemsIndexed(
                        attendees.take(showAvatarsNum), { _, attendee -> attendee.id.id }
                    ) { index, attendee ->
                        UserSquareAvatar(
                            modifier = Modifier
                                .zIndex(-index.toFloat())
                                .animateItem(),
                            drawBorder = true,
                            avatar = attendee.avatar,
                        )
                    }
                }

                if (attendees.size > showAvatarsNum) {
                    Text(
                        modifier = Modifier.padding(start = EventsTheme.sizes.sizeX5),
                        text = "+${attendees.size - showAvatarsNum}",
                        style = EventsTheme.typography.bodyText1
                    )
                }
            }
        }
    }
}
