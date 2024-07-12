package com.kekulta.events.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.navigation.GroupDetails
import com.kekulta.events.ui.navigation.findNavigator
import com.kekulta.events.ui.showcase.mockGroupsVo
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.EventsSearchField
import com.kekulta.events.ui.widgets.groupsList

@Composable
fun GroupsScreen() {
    val navigator = findNavigator()

    Column {
        EventsSearchField(
            modifier = Modifier
                /*
                    Edge padding should be unified in the whole app. It now can be X9 or X8 depending on
                    screen.
                */
                .padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
        )

        LazyColumn(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8)
        ) {
            groupsList(groups = mockGroupsVo(20), onClick = { vo ->
                navigator.navTo(GroupDetails(name = vo.name, id = vo.id, tab = navigator.currTab()))
            })
        }
    }
}
