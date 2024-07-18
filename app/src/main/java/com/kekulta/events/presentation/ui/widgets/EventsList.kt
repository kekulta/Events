package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.models.EventElementVo
import com.kekulta.events.presentation.ui.theme.EventsTheme

fun LazyListScope.eventsList(
    events: List<EventElementVo>, onClick: ((EventElementVo) -> Unit)? = null
) {
}
