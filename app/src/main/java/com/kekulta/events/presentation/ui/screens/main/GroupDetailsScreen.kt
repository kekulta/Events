package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.loremIpsum
import com.kekulta.events.presentation.ui.navigation.EventDetails
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.showcase.mockEventsVo
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.eventsList
import com.kekulta.events.presentation.viewmodel.EventId

@Composable
fun GroupDetailsScreen(id: String) {
    val navigator = findNavigator()

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = true,
            currScreenAction = null,
            /* Will be loaded from viewModel */
            currScreenName = "Group's Name"
        )
    }

    LazyColumn {

        item {
            Text(
                loremIpsum(),
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX9)
                    .padding(bottom = EventsTheme.sizes.sizeX6),
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak,
            )
        }
        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = EventsTheme.sizes.sizeX9,
                    vertical = EventsTheme.sizes.sizeX6
                ),
                text = stringResource(R.string.groups_events),
                style = EventsTheme.typography.bodyText1,
                color = EventsTheme.colors.neutralWeak,
            )
        }

        /*
            Here we will extract data from viewModel via GroupId
         */
        eventsList(
            mockEventsVo(20),
            onClick = { vo ->
                navigator.navTo(
                    EventDetails(
                        id = EventId(vo.id),
                        tab = navigator.currTab()
                    )
                )
            })
    }
}