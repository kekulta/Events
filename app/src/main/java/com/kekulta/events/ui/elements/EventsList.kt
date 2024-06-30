package com.kekulta.events.ui.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.showcase.mockEventsVo
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun EventsList(events: List<EventElementVo>) {
    LazyColumn {
        itemsIndexed(mockEventsVo(10)) { index, vo ->
            EventElement(modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .fillMaxWidth(),
                eventVo = vo,
                onClick = { /* TODO */ })
            Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
                color = EventsTheme.colors.neutralLine,
            )
            Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
        }
    }
}
