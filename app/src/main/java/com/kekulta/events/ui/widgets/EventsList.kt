package com.kekulta.events.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.models.EventElementVo
import com.kekulta.events.ui.theme.EventsTheme

fun LazyListScope.eventsList(
    events: List<EventElementVo>, onClick: ((EventElementVo) -> Unit)? = null
) {
    itemsIndexed(events) { _, vo ->
        EventElement(modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX9)
            .fillMaxWidth(),
            eventVo = vo,
            onClick = { onClick?.invoke(vo) })
        Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            color = EventsTheme.colors.neutralLine,
        )
        Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
    }
}
