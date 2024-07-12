package com.kekulta.events.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.EventsTabs
import com.kekulta.events.ui.widgets.EventsSearchField
import com.kekulta.events.ui.widgets.mockTabVo

@Composable
fun MyEventsScreen() {
    Column {
        EventsSearchField(
            modifier = Modifier
                /*
                    Edge padding should be unified in the whole app. It now can be X9 or X8 depending on
                    screen.
                */
                .padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
        )

        EventsTabs(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8),
            tabs = listOf(mockTabVo("Planned Events"), mockTabVo("Past Events"))
        )
    }
}
