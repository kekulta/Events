package com.kekulta.events.presentation.ui.widgets

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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.models.CommunityItemVo
import com.kekulta.events.presentation.ui.theme.EventsTheme

@Composable
fun CommunityItem(
    communityVo: CommunityItemVo, modifier: Modifier = Modifier, onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ), verticalAlignment = Alignment.Top
    ) {
        CommunitiesquareAvatar(
            modifier = Modifier.padding(EventsTheme.sizes.sizeX2), avatar = communityVo.avatar
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
                text = pluralStringResource(
                    id = R.plurals.members_count,
                    count = communityVo.membersCount,
                    communityVo.membersCount,
                ),
                style = EventsTheme.typography.metadata1, color = EventsTheme.colors.neutralWeak,
            )
        }
    }
}
