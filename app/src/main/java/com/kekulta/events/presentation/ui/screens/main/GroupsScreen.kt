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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.navigation.GroupDetails
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.GroupItem
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.viewmodel.GroupsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupsScreen(viewModel: GroupsScreenViewModel = koinViewModel()) {
    val state by viewModel.observeAllGroups().collectAsStateWithLifecycle()

    val navigator = findNavigator()

    fun navToDetails(id: GroupId) {
        navigator.navTo(
            GroupDetails(
                id = id,
                tab = navigator.currTab(),
            )
        )
    }

    SetTopBar {
        remember {
            EventsTopBarState(
                enabled = true,
                showBackButton = false,
                currScreenAction = null,
                currScreenName = "Groups"
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
            is ScreenState.Error -> Text(text = "Something went wrong!")
            is ScreenState.Loading -> Text(text = "Loading...")
            is ScreenState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8)
                ) {
                    val groups = s.state

                    items(groups) { vo ->
                        GroupItem(
                            modifier = Modifier
                                .padding(horizontal = EventsTheme.sizes.sizeX9)
                                .fillMaxWidth(),
                            groupVo = vo, onClick = { navToDetails(vo.id) },
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
