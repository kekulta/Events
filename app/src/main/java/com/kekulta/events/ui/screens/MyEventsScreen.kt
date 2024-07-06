package com.kekulta.events.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.widgets.EventsTabs
import com.kekulta.events.ui.widgets.mockTabVo
import com.kekulta.events.ui.widgets.SearchField
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun MyEventsScreen() {
    Column {
        SearchField(
            modifier = Modifier/*
                Edge padding should be unified in the whole app. It now can be X9 or X8 depending on
                screen.
            */.padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
        ) {
            /* TODO */
        }

        EventsTabs(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8),
            tabs = listOf(mockTabVo("Planned Events"), mockTabVo("Past Events"))
        )
    }
}
