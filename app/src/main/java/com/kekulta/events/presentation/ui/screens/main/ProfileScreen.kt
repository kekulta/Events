package com.kekulta.events.presentation.ui.screens.main

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.presentation.ui.models.ProfileVo
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.UserCircleAvatar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsButtonDefaults
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsOutlinedButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.debouncedClickable

@Composable
fun ProfileScreen(
) {

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = true,
            currScreenAction = {
                ProfileAction {
                    /* TODO */
                }
            },
            currScreenName = "Profile"
        )
    }

    val profileVO = ProfileVo(
        name = "Ruslan Russkikh",
        phone = "+7 995 917-72-42",
        avatar = Avatar(null)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserCircleAvatar(
            modifier = Modifier
                .padding(top = EventsTheme.sizes.sizeX16)
                .size(EventsTheme.sizes.sizeX100),
            avatar = profileVO.avatar,
        )
        Text(
            modifier =
            Modifier.padding(top = EventsTheme.sizes.sizeX8),
            text = profileVO.name,
            style = EventsTheme.typography.heading2
        )
        Text(
            text = profileVO.phone,
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
                    contentDescription = "Delete icon",
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
                    contentDescription = "Delete icon",
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
                    contentDescription = "Delete icon",
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
                    contentDescription = "Delete icon",
                    tint = EventsTheme.colors.brandDefault
                )
            }
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
