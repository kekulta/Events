package com.kekulta.events.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.ui.base.avatar.AddBadge
import com.kekulta.events.ui.base.avatar.BasicAvatar
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun EventSquareAvatar(
    modifier: Modifier = Modifier,
    url: String? = null,
) {
    BasicAvatar(modifier = modifier.size(EventsTheme.sizes.sizeX24), placeholder = {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.icon_event_avatar),
            contentDescription = "Default avatar"
        )
    }, shape = RoundedCornerShape(33), url = url)
}

@Composable
fun CommunitySquareAvatar(
    modifier: Modifier = Modifier,
    url: String? = null,
) {
    BasicAvatar(modifier = modifier.size(EventsTheme.sizes.sizeX24), placeholder = {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.icon_community_avatar),
            contentDescription = "Default avatar"
        )
    }, shape = RoundedCornerShape(33), url = url)
}

@Composable
fun UserCircleAddAvatar(
    modifier: Modifier = Modifier,
    url: String? = null,
    onBadgeClick: () -> Unit = {},
) {
    BasicAvatar(
        badgeSize = 0.33f,
        modifier = modifier.size(EventsTheme.sizes.sizeX50),
        placeholder = {
            Icon(
                modifier = Modifier
                    .fillMaxSize(0.66f)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.icon_avatar),
                contentDescription = "Default avatar"
            )
        },
        shape = CircleShape,
        badge = {
            AddBadge(onClick = onBadgeClick)
        },
        url = url
    )
}

@Composable
fun UserCircleAvatar(
    modifier: Modifier = Modifier,
    url: String? = null,
) {
    BasicAvatar(modifier = modifier.size(EventsTheme.sizes.sizeX50), placeholder = {
        Icon(
            modifier = Modifier
                .fillMaxSize(0.33f)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.icon_avatar),
            contentDescription = "Default avatar"
        )
    }, shape = CircleShape, url = url)
}


@Composable
fun UserSquareAvatar(
    modifier: Modifier = Modifier,
    drawBorder: Boolean = false,
    url: String? = null,
) {
    BasicAvatar(
        modifier = modifier.size(EventsTheme.sizes.sizeX24), placeholder = {
            Icon(
                modifier = Modifier
                    .fillMaxSize(0.66f)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.icon_avatar),
                contentDescription = "Default avatar"
            )
        }, borderStroke = if (drawBorder) BorderStroke(
            EventsTheme.sizes.sizeX1, EventsTheme.colors.brandBackground
        ) else BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(33), url = url
    )
}
