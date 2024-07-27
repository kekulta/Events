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
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.presentation.ui.models.CommunityDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.navigation.EventDetails
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventItem
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.viewmodel.CommunityDetailsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityDetailsScreen(id: CommunityId, viewModel: CommunityDetailsScreenViewModel = koinViewModel()) {
    viewModel.setId(id)
    val state by viewModel.observeState().collectAsStateWithLifecycle()

    val navigator = requireNavigator()

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
                val screenName = stringResource(id = R.string.topbar_loading)

                remember(screenName) {
                    EventsTopBarState(
                        currScreenName = screenName,
                    )
                }
            }
        }

        is ScreenState.Error -> {

            SetTopBar {
                val screenName = stringResource(id = R.string.topbar_error)

                remember(screenName) {
                    EventsTopBarState(
                        currScreenName = screenName
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
private fun SuccessScreen(vo: CommunityDetailsVo, onClick: (id: EventId) -> Unit) {

    SetTopBar {
        remember(vo) {
            EventsTopBarState(
                currScreenName = vo.name,
            )
        }
    }

    LazyColumn {
        item {
            Text(
                vo.description ?: stringResource(id = R.string.community_no_description),
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX9)
                    .padding(bottom = EventsTheme.sizes.sizeX6),
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak,
            )
        }
        item {
            val communitiesHeading = if (vo.events.isEmpty()) {
                stringResource(id = R.string.community_no_events)
            } else {
                stringResource(R.string.communities_events)
            }

            Text(
                modifier = Modifier.padding(
                    horizontal = EventsTheme.sizes.sizeX9, vertical = EventsTheme.sizes.sizeX6
                ),
                text = communitiesHeading,
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