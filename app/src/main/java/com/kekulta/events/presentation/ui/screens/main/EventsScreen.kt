package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.navigation.EventDetails
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.ActiveEventItem
import com.kekulta.events.presentation.ui.widgets.EventItem
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.EventsTab
import com.kekulta.events.presentation.ui.widgets.EventsTabs
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.debouncedClickable
import com.kekulta.events.presentation.ui.models.ActiveEventItemVo
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.presentation.viewmodel.EventsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventsScreen(viewModel: EventsScreenViewModel = koinViewModel()) {

    val navigator = findNavigator()
    val allEventsState by viewModel.observeAllEvents().collectAsStateWithLifecycle()
    val activeEventsState by viewModel.observeActiveEvents().collectAsStateWithLifecycle()

    fun navToDetails(id: EventId) {
        navigator.navTo(
            EventDetails(
                id = id, tab = navigator.currTab()
            )
        )
    }

    SetTopBar {
        EventsTopBarState(
            enabled = true, showBackButton = false, currScreenAction = {
                EventsAction {
                    /* TODO */
                }
            }, currScreenName = "Events"
        )
    }

    Column {
        EventsSearchField(
            modifier = Modifier
                // Edge padding should be unified in the whole app. It now can be X9 or X8 depending
                // on screen.
                .padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
        )

        EventsTabs(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8), tabs = listOf(
                EventsTab(
                    title = "All Events",
                    content = {
                        when (val events = allEventsState) {
                            is ScreenState.Error -> Text("Error")
                            is ScreenState.Loading -> Text("Loading")
                            is ScreenState.Success -> AllEventsTab(
                                events = events.state, onClick = ::navToDetails
                            )
                        }
                    },
                ),
                EventsTab(
                    title = "Active Events",
                    content = {
                        when (val events = activeEventsState) {
                            is ScreenState.Error -> Text("Error")
                            is ScreenState.Loading -> Text("Loading")
                            is ScreenState.Success -> ActiveEventsTab(
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

@Composable
private fun ActiveEventsTab(events: List<ActiveEventItemVo>, onClick: ((id: EventId) -> Unit)?) {
    LazyColumn {
        itemsIndexed(events) { _, vo ->
            ActiveEventItem(modifier = Modifier
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

@Composable
private fun AllEventsTab(events: List<EventItemVo>, onClick: ((id: EventId) -> Unit)?) {
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
