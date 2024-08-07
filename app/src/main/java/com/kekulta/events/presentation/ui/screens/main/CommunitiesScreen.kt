package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.navigation.CommunityDetails
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.CommunityItem
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.viewmodel.CommunitiesScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunitiesScreen(viewModel: CommunitiesScreenViewModel = koinViewModel()) {
    val state by viewModel.observeAllCommunities().collectAsStateWithLifecycle()

    val navigator = requireNavigator()

    fun navToDetails(id: CommunityId) {
        navigator.navTo(
            CommunityDetails(
                id = id,
                tab = navigator.currTab(),
            )
        )
    }

    SetTopBar {
        val screenName = stringResource(id = R.string.screen_communities)

        remember(screenName) {
            EventsTopBarState(
                showBackButton = false,
                currScreenName = screenName
            )
        }
    }

    Column {
        EventsSearchField(
            modifier = Modifier
                // Edge padding should be unified in the whole app. It now can be X9 or X8 depending
                // on screen.
                .padding(horizontal = EventsTheme.sizes.sizeX8), state = rememberTextFieldState()
        )

        when (val s = state) {
            is ScreenState.Error -> Text(stringResource(id = R.string.error_message))
            is ScreenState.Loading -> Text(stringResource(id = R.string.loading_message))
            is ScreenState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8)
                ) {
                    val communities = s.state

                    items(communities) { vo ->
                        CommunityItem(
                            modifier = Modifier
                                .padding(horizontal = EventsTheme.sizes.sizeX9)
                                .fillMaxWidth(),
                            communityVo = vo, onClick = { navToDetails(vo.id) },
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(
                                horizontal = EventsTheme.sizes.sizeX9,
                                vertical = EventsTheme.sizes.sizeX6
                            ),
                            color = EventsTheme.colors.neutralLine,
                        )
                    }
                }
            }
        }
    }
}
