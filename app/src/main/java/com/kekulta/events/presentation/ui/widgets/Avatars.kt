package com.kekulta.events.presentation.ui.widgets

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.base.avatar.AddBadge
import com.kekulta.events.presentation.ui.widgets.base.avatar.BasicAvatar

@Composable
fun EventSquareAvatar(
    modifier: Modifier = Modifier,
    avatar: Avatar = Avatar(null),
) {
    BasicAvatar(modifier = modifier.size(EventsTheme.sizes.sizeX24), placeholder = {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.icon_event_avatar),
            contentDescription = stringResource(id = R.string.default_avatar_description),
        )
    }, shape = RoundedCornerShape(33), avatar = avatar)
}

@Composable
fun GroupSquareAvatar(
    modifier: Modifier = Modifier,
    avatar: Avatar = Avatar(null),
) {
    BasicAvatar(modifier = modifier.size(EventsTheme.sizes.sizeX24), placeholder = {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.icon_community_avatar),
            contentDescription = stringResource(id = R.string.default_avatar_description),
        )
    }, shape = RoundedCornerShape(33), avatar = avatar)
}

@Composable
fun UserCircleAddAvatar(
    modifier: Modifier = Modifier,
    avatar: Avatar = Avatar(null),
    onBadgeClick: () -> Unit = {},
) {
    BasicAvatar(
        badgeSize = 0.33f,
        modifier = modifier.size(EventsTheme.sizes.sizeX50),
        placeholder = {
            Icon(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.icon_avatar),
                contentDescription = stringResource(id = R.string.default_avatar_description),
            )
        },
        shape = CircleShape,
        badge = {
            AddBadge(onClick = onBadgeClick)
        },
        avatar = avatar
    )
}

@Composable
fun UserCircleAvatar(
    modifier: Modifier = Modifier,
    avatar: Avatar = Avatar(null),
) {
    BasicAvatar(modifier = modifier.size(EventsTheme.sizes.sizeX50), placeholder = {
        Icon(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.icon_avatar),
            contentDescription = stringResource(id = R.string.default_avatar_description),
        )
    }, shape = CircleShape, avatar = avatar)
}


@Composable
fun UserSquareAvatar(
    modifier: Modifier = Modifier,
    drawBorder: Boolean = false,
    avatar: Avatar = Avatar(null),
) {
    BasicAvatar(
        modifier = modifier.size(EventsTheme.sizes.sizeX24),
        placeholder = {
            Icon(
                modifier = Modifier
                    .fillMaxSize(0.66f)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.icon_avatar),
                contentDescription = stringResource(id = R.string.default_avatar_description),
            )
        },
        borderStroke = if (drawBorder) BorderStroke(
            EventsTheme.sizes.sizeX1, EventsTheme.colors.brandBackground
        ) else BorderStroke(0.dp, Color.Transparent),
        shape = RoundedCornerShape(33),
        avatar = avatar
    )
}
