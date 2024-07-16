package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.theme.EventsTheme

@Composable
fun SettingsItem(
    icon: Painter,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(vertical = EventsTheme.sizes.sizeX4)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null, onClick = onClick,
            )
    ) {
        Icon(
            modifier = Modifier.size(EventsTheme.sizes.sizeX12),
            painter = icon,
            tint = EventsTheme.colors.neutralActive,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(start = EventsTheme.sizes.sizeX3)
                .weight(1f),
            text = name,
            style = EventsTheme.typography.bodyText1
        )
        Icon(
            modifier = Modifier.size(EventsTheme.sizes.sizeX12),
            painter = painterResource(id = R.drawable.icon_arr_right),
            tint = EventsTheme.colors.neutralActive,
            contentDescription = null
        )
    }
}
