package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.presentation.ui.models.ProfileVo
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.MyEvents
import com.kekulta.events.presentation.ui.navigation.Profile
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.ProfileItem
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.SettingsItem

@Composable
fun MoreScreen(
) {
    val navigator = findNavigator()

    val topBarState = remember {
        EventsTopBarState(
            enabled = true,
            showBackButton = false,
            currScreenAction = null,
            currScreenName = "More"
        )
    }

    SetTopBar {
        topBarState
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX8),
    ) {
        item {
            ProfileItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                profileVo = ProfileVo(
                    name = "Ruslan Russkikh", phone = "+7 995 917-72-42", avatar = Avatar(null)
                ),
                onClick = { navigator.navTo(Profile()) })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX6),
                icon = painterResource(id = R.drawable.icon_events),
                name = "My events",
                onClick = {
                    navigator.navTo(MyEvents())
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_theme),
                name = "Theme",
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_notification),
                name = "Notifications",
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_safety),
                name = "Safety",
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_res),
                name = "Resources",
                onClick = {
                    /* TODO */
                })
        }
        item {
            HorizontalDivider(color = EventsTheme.colors.neutralLine)
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_help),
                name = "Help",
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_mail),
                name = "Invite friends",
                onClick = {
                    /* TODO */
                })
        }
        item {
            HorizontalDivider(color = EventsTheme.colors.neutralLine)
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_pencil),
                name = "Login Flow",
                onClick = {
                    navigator.setRoot(EnterPhone())
                })
        }
    }
}