package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.UserCircleAvatar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsButtonDefaults
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsOutlinedButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsTextButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.debouncedClickable
import com.kekulta.events.presentation.viewmodel.ProfileScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    val state by viewModel.observeProfileState().collectAsStateWithLifecycle()

    SetTopBar {
        val screenName = stringResource(id = R.string.screen_profile)

        remember {
            EventsTopBarState(currScreenName = screenName)
        }
    }

    /*
        `when` used for smartcast
     */
    when (val s = state) {
        null -> LoggedOutProfile()
        else -> LoggedInProfile(vo = s, viewModel::logOut)
    }
}

@Composable
private fun LoggedOutProfile() {
    val navigator = requireNavigator()

    SetTopBar {
        val screenName = stringResource(id = R.string.screen_profile)

        remember {
            EventsTopBarState(currScreenName = screenName)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.continue_with_account),
            style = EventsTheme.typography.subheading2,
        )
        EventsFilledButton(
            modifier = Modifier
                // Paddings are *mess*
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .fillMaxWidth(),
            onClick = { navigator.setRoot(EnterPhone()) },
        ) {
            Text(text = stringResource(id = R.string.login_button))
        }
    }
}

@Composable
private fun LoggedInProfile(vo: ProfileDetailsVo, logOut: () -> Unit) {
    SetTopBar {
        val screenName = stringResource(id = R.string.screen_profile)

        remember {
            EventsTopBarState(
                currScreenAction = {
                    ProfileAction {
                        /* TODO */
                    }
                },
                currScreenName = screenName,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserCircleAvatar(
            modifier = Modifier
                .padding(top = EventsTheme.sizes.sizeX16)
                .size(EventsTheme.sizes.sizeX100),
            avatar = vo.avatar,
        )
        Text(
            modifier = Modifier.padding(top = EventsTheme.sizes.sizeX8),
            text = vo.name,
            style = EventsTheme.typography.heading2
        )
        Text(
            text = vo.number,
            style = EventsTheme.typography.bodyText2,
            color = EventsTheme.colors.neutralWeak,
        )
        Row(
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX8)
                .padding(top = EventsTheme.sizes.sizeX10)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_twittter),
                    contentDescription = stringResource(id = R.string.twitter_button_description),
                    tint = EventsTheme.colors.brandDefault
                )
            }
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_inst),
                    contentDescription = stringResource(id = R.string.instagram_button_description),
                    tint = EventsTheme.colors.brandDefault
                )
            }
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_in),
                    contentDescription = stringResource(id = R.string.linkedin_button_description),
                    tint = EventsTheme.colors.brandDefault
                )
            }
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_fb),
                    contentDescription = stringResource(id = R.string.facebook_button_description),
                    tint = EventsTheme.colors.brandDefault
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        EventsTextButton(onClick = { logOut() }) {
            Text(
                text = stringResource(id = R.string.logout_button),
                style = EventsTheme.typography.subheading2,
            )
        }
    }
}

@Composable
fun ProfileAction(onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(EventsTheme.sizes.sizeX12)
            .debouncedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null, onClick = onClick
            ),
        painter = painterResource(id = R.drawable.icon_pencil),
        tint = EventsTheme.colors.neutralActive,
        contentDescription = null
    )
}
