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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.MyEvents
import com.kekulta.events.presentation.ui.navigation.Profile
import com.kekulta.events.presentation.ui.navigation.requireNavigator
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

    val navigator = requireNavigator()

    SetTopBar {
        val screenName = stringResource(id = R.string.screen_more)

        remember(screenName) {
            EventsTopBarState(
                showBackButton = false,
                currScreenName = screenName,
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
                    name = stringResource(id = R.string.item_my_events),
                    onClick = {
                        navigator.navTo(MyEvents())
                    })
            }
        } else {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.continue_with_account),
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
                    Text(text = stringResource(id = R.string.login_button))
                }
            }
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_theme),
                name = stringResource(id = R.string.item_theme),
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_notification),
                name = stringResource(id = R.string.item_notifications),
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_safety),
                name = stringResource(id = R.string.item_safety),
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_res),
                name = stringResource(id = R.string.item_resources),
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
                name = stringResource(id = R.string.item_help),
                onClick = {
                    /* TODO */
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_mail),
                name = stringResource(id = R.string.item_invite),
                onClick = {
                    /* TODO */
                })
        }
    }
}