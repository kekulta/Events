package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.base.chips.RoundChip
import com.kekulta.events.presentation.ui.models.ActiveEventItemVo

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ActiveEventItem(
    eventVo: ActiveEventItemVo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ), verticalAlignment = Alignment.Top
    ) {
        EventSquareAvatar(
            modifier = Modifier.padding(EventsTheme.sizes.sizeX2),
            avatar = eventVo.avatar
        )
        Column(
            modifier = Modifier
                .padding(start = EventsTheme.sizes.sizeX6),
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = eventVo.name,
                    style = EventsTheme.typography.bodyText1
                )
            }
            Text(
                text = "${eventVo.date} — ${eventVo.location}",
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(EventsTheme.sizes.sizeX2),
                verticalArrangement = Arrangement.spacedBy(EventsTheme.sizes.sizeX2),
                modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX2),
            ) {
                eventVo.tags.forEach { tag ->
                    RoundChip(text = tag)
                }
            }
        }
    }
}
