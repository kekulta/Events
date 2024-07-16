package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.models.ProfileVo
import com.kekulta.events.presentation.ui.theme.EventsTheme

@Composable
fun ProfileItem(
    profileVo: ProfileVo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null, onClick = onClick,
            )
    ) {
        UserCircleAvatar(
            modifier = Modifier.size(EventsTheme.sizes.sizeX25),
            url = profileVo.avatar,
        )
        Column(
            modifier = Modifier
                .padding(start = EventsTheme.sizes.sizeX10)
                .weight(1f)
        ) {
            Text(text = profileVo.name, style = EventsTheme.typography.bodyText1)
            Text(
                text = profileVo.phone,
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak,
            )
        }
        Icon(
            modifier = Modifier.size(EventsTheme.sizes.sizeX12),
            painter = painterResource(id = R.drawable.icon_arr_right),
            tint = EventsTheme.colors.neutralActive,
            contentDescription = null
        )
    }
}

