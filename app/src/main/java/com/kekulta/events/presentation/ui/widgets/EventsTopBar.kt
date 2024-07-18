package com.kekulta.events.presentation.ui.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.navigation.getOnBackPressedDispatcher
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.base.buttons.debouncedClickable

/*
    Paddings are mess. Will fix that later.

    Events prefix can mean either Events tab or Events as an app name.
    And it is impossible to differentiate by the name alone.
    TODO: Should be renamed.
 */

val LocalEventsTopBarState = staticCompositionLocalOf<MutableState<EventsTopBarState>?> {
    null
}

@Composable
fun ProvideEventsTopBarState(
    state: MutableState<EventsTopBarState>,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalEventsTopBarState provides state
    ) {
        content()
    }
}

@Composable
fun SetTopBar(builder: (EventsTopBarState) -> EventsTopBarState) {
    val topBarState = LocalEventsTopBarState.current
    if (topBarState != null) {
        topBarState.value = builder(topBarState.value)
    }
}

data class EventsTopBarState(
    val enabled: Boolean,
    val showBackButton: Boolean,
    val currScreenAction: @Composable (() -> Unit)?,
    val currScreenName: String,
)

@Composable
fun EventsTopBar(
    state: State<EventsTopBarState>,
) {
    val topBarState by state
    val onBackPressedDispatcher = getOnBackPressedDispatcher()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .statusBarsPadding()
            .padding(vertical = EventsTheme.sizes.sizeX6)
    ) {
        AnimatedVisibility(
            visible = state.value.showBackButton,
            enter = fadeIn() + expandHorizontally(),
            exit = shrinkHorizontally() + fadeOut(),
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = EventsTheme.sizes.sizeX8)
                    .size(EventsTheme.sizes.sizeX12)
                    .debouncedClickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null,
                        onClick = onBackPressedDispatcher::onBackPressed
                    ),
                painter = painterResource(id = R.drawable.icon_arr_left),
                tint = EventsTheme.colors.neutralActive,
                contentDescription = null
            )
        }

        val namePadding by animateDpAsState(
            targetValue = if (!topBarState.showBackButton) EventsTheme.sizes.sizeX12 else EventsTheme.sizes.sizeX4,
            label = "Name padding"
        )

        Text(
            maxLines = 1,
            modifier = Modifier
                .padding(start = namePadding)
                .weight(1f),
            text = topBarState.currScreenName,
            overflow = TextOverflow.Ellipsis,
            style = EventsTheme.typography.subheading1,
        )

        AnimatedContent(targetState = topBarState.currScreenAction, transitionSpec = {
            slideInHorizontally { width -> width } + fadeIn() togetherWith slideOutHorizontally { width -> width } + fadeOut()
        }, label = "Top Bar Action") { newAction ->
            if (newAction != null) {
                Box(modifier = Modifier.padding(end = EventsTheme.sizes.sizeX12)) {
                    newAction.invoke()
                }
            }
        }
    }
}

