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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.theme.EventsTheme

@Composable
fun BasicAvatar(
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit),
    shape: Shape,
    url: String? = null,
    badgeSize: Float = 0.20f,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    badge: @Composable (BoxScope.() -> Unit)? = null
) {

    /* We shouldn't actually store state in composable due to possible configuration change and data
    loss but here it seems to be ok, because we're actually gonna lost our progress on configuration
    change. Yet I'm still not sure if it will behave correctly all the time. */
    var isLoaded by remember {
        mutableStateOf(false)
    }

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
            if (!isLoaded) {
                placeholder()
            }

            if (url != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clip(shape),
                    model = url,
                    onSuccess = { isLoaded = true },
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

