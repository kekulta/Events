package com.kekulta.events.presentation.ui.widgets.base.avatar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kekulta.events.R
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.presentation.ui.theme.EventsTheme

@Composable
fun BasicAvatar(
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit),
    shape: Shape,
    avatar: Avatar = Avatar(null),
    badgeSize: Float = 0.20f,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    badge: @Composable (BoxScope.() -> Unit)? = null
) {

    Box(
        modifier = modifier
            .background(
                EventsTheme.colors.neutralOffWhite, shape
            )
            /* Default size value. Will be overridden by any earlier modifier if there are any */
            .size(EventsTheme.sizes.sizeX24), contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(borderStroke, shape)
                .padding(borderStroke.width)
                .fillMaxSize()
        ) {

            /*
                Always show placeholder. Otherwise it may not be showing in layouts when data changed.
                This could be linked to some kind of holders reuse or idk.
             */
            placeholder()

            if (avatar.url != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clip(shape),
                    model = avatar.url,
                    contentDescription = "Avatar",
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(badgeSize)
                .align(Alignment.BottomEnd)
        ) {
            badge?.invoke(this)
        }
    }
}

@Composable
fun AddBadge(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.icon_plus),
        modifier = modifier
            .fillMaxSize(0.7f)
            .clip(CircleShape)
            .background(Color.Black)
            .clickable(onClick = onClick),
        contentDescription = "Add badge",
        tint = EventsTheme.colors.neutralOffWhite,
    )
}

