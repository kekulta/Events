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
import androidx.compose.ui.unit.dp
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
            modifier = Modifier.padding(start = 24.dp, end = 20.dp), url = communityVo.avatar
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = communityVo.name,
                style = EventsTheme.typography.bodyText1
            )

            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = communityVo.members,
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak
            )
        }
    }
}
