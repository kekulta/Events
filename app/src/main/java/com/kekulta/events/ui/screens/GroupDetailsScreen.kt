package com.kekulta.events.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kekulta.events.R
import com.kekulta.events.ui.loremIpsum
import com.kekulta.events.ui.navigation.EventDetails
import com.kekulta.events.ui.navigation.findNavigator
import com.kekulta.events.ui.showcase.mockEventsVo
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.eventsList

@Composable
fun GroupDetailsScreen(id: String) {
    val navigator = findNavigator()

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
                        id = vo.id,
                        name = vo.name,
                        tab = navigator.currTab()
                    )
                )
            })
    }
}