package com.kekulta.events.ui.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kekulta.events.R
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    url: String? = null,
    badge: @Composable (BoxScope.() -> Unit)? = null
) {
    val isLoaded = remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        if (!isLoaded.value) {
            Icon(
                modifier = Modifier
                    .background(EventsTheme.colors.neutralOffWhite, CircleShape)
                    .size(100.dp)
                    .padding(22.dp),
                painter = painterResource(id = R.drawable.icon_avatar),
                contentDescription = "Default avatar"
            )
        }

        if (url != null) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                model = url,
                onSuccess = { isLoaded.value = true },
                contentDescription = "Avatar",
            )
        }
        badge?.invoke(this)
    }
}

@Composable
fun MeetAvatar(
    modifier: Modifier = Modifier,
    url: String? = null,
    badge: @Composable (BoxScope.() -> Unit)? = null
) {
    val isLoaded = remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        if (!isLoaded.value) {
            Image(
                modifier = Modifier
                    .background(EventsTheme.colors.neutralOffWhite, RoundedCornerShape(16.dp))
                    .size(48.dp),
                painter = painterResource(id = R.drawable.icon_meet_avatar),
                contentDescription = "Default avatar"
            )
        }

        if (url != null) {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = url,
                onSuccess = { isLoaded.value = true },
                contentDescription = "Avatar",
            )
        }
        badge?.invoke(this)
    }
}

