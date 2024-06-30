package com.kekulta.events.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun EventsScreen() {
    SearchField(
        modifier = Modifier
            /*
                Edge padding should be unified in the whole app. It now can be X9 or X8 depending on
                screen.
            */
            .padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
    ) {
        /* TODO */
    }


}
