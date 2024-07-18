package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.navigation.GroupDetails
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.groupsList

@Composable
fun GroupsScreen() {
    val navigator = findNavigator()

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = false,
            currScreenAction = null,
            currScreenName = "Groups"
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

        LazyColumn(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8)
        ) {
//            groupsList(groups = mockGroupsVo(20), onClick = { vo ->
//                navigator.navTo(GroupDetails(id = vo.id, tab = navigator.currTab()))
//            })
        }
    }
}
