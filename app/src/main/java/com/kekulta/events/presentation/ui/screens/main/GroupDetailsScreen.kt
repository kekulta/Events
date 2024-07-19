package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.presentation.ui.models.GroupDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.navigation.EventDetails
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventItem
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.viewmodel.GroupDetailsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupDetailsScreen(id: GroupId, viewModel: GroupDetailsScreenViewModel = koinViewModel()) {
    viewModel.setId(id)
    val state by viewModel.observeState().collectAsStateWithLifecycle()

    val navigator = findNavigator()

    fun navToEvent(id: EventId) {
        navigator.navTo(
            EventDetails(
                id = id,
                tab = navigator.currTab(),
            )
        )
    }

    when (val s = state) {
        is ScreenState.Loading -> {
            SetTopBar {
                remember {
                    EventsTopBarState(
                        enabled = true,
                        showBackButton = true,
                        currScreenAction = null,
                        currScreenName = "Loading.."
                    )
                }
            }
        }

        is ScreenState.Error -> {
            SetTopBar {
                remember {
                    EventsTopBarState(
                        enabled = true,
                        showBackButton = true,
                        currScreenAction = null,
                        currScreenName = "Error.."
                    )
                }
            }
        }

        is ScreenState.Success -> {
            SuccessScreen(vo = s.state, ::navToEvent)
        }
    }
}

@Composable
private fun SuccessScreen(vo: GroupDetailsVo, onClick: (id: EventId) -> Unit) {

    SetTopBar {
        remember(vo) {
            EventsTopBarState(
                enabled = true,
                showBackButton = true,
                currScreenAction = null,
                currScreenName = vo.name,
            )
        }
    }

    LazyColumn {
        item {
            Text(
                vo.description,
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX9)
                    .padding(bottom = EventsTheme.sizes.sizeX6),
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak,
            )
        }
        item {
            val groupsHeading = if (vo.events.isEmpty()) {
                "Group has no events"
            } else {
                stringResource(R.string.groups_events)
            }

            Text(
                modifier = Modifier.padding(
                    horizontal = EventsTheme.sizes.sizeX9, vertical = EventsTheme.sizes.sizeX6
                ),
                text = groupsHeading,
                style = EventsTheme.typography.bodyText1,
                color = EventsTheme.colors.neutralWeak,
            )
        }

        items(vo.events) { eventVo ->
            EventItem(modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .fillMaxWidth(),
                eventVo = eventVo,
                onClick = { onClick.invoke(eventVo.id) })
            HorizontalDivider(
                modifier = Modifier.padding(
                    horizontal = EventsTheme.sizes.sizeX9, vertical = EventsTheme.sizes.sizeX6
                ),
                color = EventsTheme.colors.neutralLine,
            )
        }
    }
}