package com.kekulta.events.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kekulta.events.ui.models.GroupElementVo
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun GroupElement(
    groupVo: GroupElementVo, modifier: Modifier = Modifier, onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ), verticalAlignment = Alignment.Top
    ) {
        GroupSquareAvatar(
            modifier = Modifier.padding(EventsTheme.sizes.sizeX2), url = groupVo.avatar
        )
        Column(
            modifier = Modifier.padding(start = EventsTheme.sizes.sizeX6)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = groupVo.name,
                style = EventsTheme.typography.bodyText1
            )

            Text(
                text = groupVo.members,
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak
            )
        }
    }
}
