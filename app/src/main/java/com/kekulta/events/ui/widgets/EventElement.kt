package com.kekulta.events.ui.widgets

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
import com.kekulta.events.ui.widgets.base.chips.RoundChip
import com.kekulta.events.ui.theme.EventsTheme

data class EventElementVo(
    val name: String,
    val avatar: String?,
    /* TODO: This is VO representation. This data shouldn't be stored like that,
         it should be stored in appropriate classes with regards to locale(i.e. timezone and
          language). As well as we shouldn't pass these classes to the View layer. All formatting
          must be done in ViewModel via specialised formatters */
    val date: String,
    val note: String?,
    val place: String,
    val tags: List<String>,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventElement(eventVo: EventElementVo, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ), verticalAlignment = Alignment.Top
    ) {
        EventSquareAvatar(
            modifier = Modifier.padding(EventsTheme.sizes.sizeX2),
            url = eventVo.avatar
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
                if (eventVo.note != null) {
                    Text(
                        text = eventVo.note,
                        color = EventsTheme.colors.neutralWeak,
                        style = EventsTheme.typography.metadata2,
                    )
                }
            }
            Text(
                text = "${eventVo.date} — ${eventVo.place}",
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
