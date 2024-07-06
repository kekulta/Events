package com.kekulta.events.ui.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.EventsTabs
import com.kekulta.events.ui.widgets.SearchField
import com.kekulta.events.ui.widgets.base.buttons.debouncedClickable
import com.kekulta.events.ui.widgets.mockTabVo

@Composable
fun EventsScreen() {
    Column {
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

        EventsTabs(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8),
            tabs = listOf(mockTabVo("All Events"), mockTabVo("Active Events"))
        )
    }
}

@Composable
fun EventsAction(onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(EventsTheme.sizes.sizeX12)
            .debouncedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null, onClick = onClick
            ),
        painter = painterResource(id = R.drawable.icon_plus),
        tint = EventsTheme.colors.neutralActive,
        contentDescription = null
    )
}
