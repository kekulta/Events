package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.MyEvents
import com.kekulta.events.presentation.ui.navigation.Profile
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.ProfileItem
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.SettingsItem
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.viewmodel.MoreScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoreScreen(
    viewModel: MoreScreenViewModel = koinViewModel()
) {
    val state by viewModel.observeState().collectAsStateWithLifecycle()

    val navigator = findNavigator()

    SetTopBar {
        remember {
            EventsTopBarState(
                enabled = true,
                showBackButton = false,
                currScreenAction = null,
                currScreenName = "More"
            )
        }
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX8),
    ) {
        val s = state
        if (s != null) {
            item {
                ProfileItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                    profileDetailsVo = s,
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
        } else {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Continue with your account.",
                    style = EventsTheme.typography.subheading2,
                    textAlign = TextAlign.Center,
                )
            }
            item {
                EventsFilledButton(modifier = Modifier
                    // Paddings are *mess*
                    .padding(horizontal = EventsTheme.sizes.sizeX5)
                    .fillMaxWidth(),
                    onClick = { navigator.setRoot(EnterPhone()) }) {
                    Text(text = "Login")
                }
            }
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
    }
}