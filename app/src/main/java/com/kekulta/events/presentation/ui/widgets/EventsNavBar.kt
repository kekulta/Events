package com.kekulta.events.presentation.ui.widgets

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.EventsNavBarState
import com.kekulta.events.presentation.ui.navigation.Groups
import com.kekulta.events.presentation.ui.navigation.More
import com.kekulta.events.presentation.ui.navigation.Tab
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.base.modifiers.advancedShadow

@Composable
fun EventsNavBar(
    state: State<EventsNavBarState>
) {
    val navigator = requireNavigator()
    val currentTab = state.value.tab

    if (currentTab != Tab.NO_BAR) {
        NavigationBar(
            modifier = Modifier.advancedShadow(
                color = Color(0x0A00000A),
                borderRadius = 0.dp,
                blurRadius = 24.dp,
                offsetY = (-1).dp,
                spread = 0f,
            ), containerColor = EventsTheme.colors.neutralWhite
        ) {
            EventsNavigationItem(
                selected = currentTab == Tab.EVENTS,
                onClick = { navigator.navTo(Events()) },
                icon = R.drawable.icon_events,
                name = R.string.tab_events,
            )

            EventsNavigationItem(
                selected = currentTab == Tab.GROUPS,
                onClick = { navigator.navTo(Groups()) },
                icon = R.drawable.icon_groups,
                name = R.string.tab_groups,
            )

            EventsNavigationItem(
                selected = currentTab == Tab.MORE,
                onClick = { navigator.navTo(More()) },
                icon = R.drawable.icon_more,
                name = R.string.tab_more,
            )
        }
    }
}
