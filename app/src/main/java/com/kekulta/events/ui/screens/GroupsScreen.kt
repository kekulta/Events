package com.kekulta.events.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.widgets.SearchField
import com.kekulta.events.ui.widgets.groupsList
import com.kekulta.events.ui.showcase.mockGroupsVo
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun GroupsScreen() {
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

        LazyColumn(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8)
        ) {
            groupsList(groups = mockGroupsVo(20))
        }
    }
}
