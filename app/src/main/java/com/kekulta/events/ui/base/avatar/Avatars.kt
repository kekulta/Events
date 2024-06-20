package com.kekulta.events.ui.base.avatar

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kekulta.events.R
import com.kekulta.events.ui.theme.EventsTheme

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
    val isLoaded = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .background(
                EventsTheme.colors.neutralOffWhite, shape
            )
            .size(48.dp), contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(borderStroke, shape)
                .padding(borderStroke.width)
                .fillMaxSize()
        ) {
            if (!isLoaded.value) {
                placeholder()
            }

            if (url != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .clip(shape),
                    model = url,
                    onSuccess = { isLoaded.value = true },
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

