package com.kekulta.events.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kekulta.events.ui.theme.EventsTheme

data class CommunityElementVo(
    val name: String,
    val avatar: String?,
    /* TODO: Add plurals */
    val members: String,
)

@Composable
fun CommunityElement(
    communityVo: CommunityElementVo, modifier: Modifier = Modifier, onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ), verticalAlignment = Alignment.Top
    ) {
        CommunitySquareAvatar(
            modifier = Modifier.padding(EventsTheme.sizes.sizeX2), url = communityVo.avatar
        )
        Column(
            modifier = Modifier.padding(start = EventsTheme.sizes.sizeX6)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = communityVo.name,
                style = EventsTheme.typography.bodyText1
            )

            Text(
                text = communityVo.members,
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak
            )
        }
    }
}
