package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.navigation.EventDetails
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventItem
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.EventsTab
import com.kekulta.events.presentation.ui.widgets.EventsTabs
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.viewmodel.MyEventsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyEventsScreen(viewModel: MyEventsScreenViewModel = koinViewModel()) {
    val plannedEventsState by viewModel.observePlannedEvents().collectAsStateWithLifecycle()
    val pastEventsState by viewModel.observePastEvents().collectAsStateWithLifecycle()

    val navigator = requireNavigator()

    fun navToDetails(id: EventId) {
        navigator.navTo(
            EventDetails(
                id = id, tab = navigator.currTab()
            )
        )
    }

    SetTopBar {
        remember {
            EventsTopBarState(
                enabled = true,
                showBackButton = true,
                currScreenAction = null,
                currScreenName = "My Events Screen"
            )
        }
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

        EventsTabs(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8), tabs = listOf(
                EventsTab(
                    title = "Planned Events",
                    content = {
                        when (val events = plannedEventsState) {
                            is ScreenState.Error -> Text("Error")
                            is ScreenState.Loading -> Text("Loading")
                            is ScreenState.Success -> EventsTab(
                                events = events.state, onClick = ::navToDetails
                            )
                        }
                    },
                ),
                EventsTab(
                    title = "Past Events",
                    content = {
                        when (val events = pastEventsState) {
                            is ScreenState.Error -> Text("Error")
                            is ScreenState.Loading -> Text("Loading")
                            is ScreenState.Success -> EventsTab(
                                events = events.state, onClick = ::navToDetails
                            )
                        }
                    },
                ),
            )
        )
    }
}

@Composable
private fun EventsTab(events: List<EventItemVo>, onClick: ((id: EventId) -> Unit)?) {
    LazyColumn {
        itemsIndexed(events) { _, vo ->
            EventItem(modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .fillMaxWidth(),
                eventVo = vo,
                onClick = { onClick?.invoke(vo.id) })
            HorizontalDivider(
                modifier = Modifier.padding(
                    horizontal = EventsTheme.sizes.sizeX9, vertical = EventsTheme.sizes.sizeX6
                ),
                color = EventsTheme.colors.neutralLine,
            )
        }
    }
}
