package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar

@Composable
fun MyEventsScreen() {

    rememberTextFieldState()
    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = true,
            currScreenAction = null,
            currScreenName = "My Events Screen"
        )
    }

    Column {
        EventsSearchField(
            modifier = Modifier
                /*
                    Edge padding should be unified in the whole app. It now can be X9 or X8 depending on
                    screen.
                */
                .padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
        )

//        EventsTabs(
//            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8),
//            tabs = listOf(mockTabVo("Planned Events"), mockTabVo("Past Events"))
//        )
    }
}
