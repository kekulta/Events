package com.kekulta.events.ui.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.ui.base.modifiers.advancedShadow
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun EventsNavBar(
    currentTab: Tab,
    onClick: (tab: Tab) -> Unit,
) {

    if (currentTab != Tab.NO_BAR) {
        NavigationBar(
            modifier = Modifier.advancedShadow(
                color = Color(0x0A00000A),
                borderRadius = 0.dp,
                blurRadius = 24.dp,
                offsetY = (-1).dp,
                spread = 0f,
            ), containerColor = EventsTheme.colors.neutralWhite
        ) {
            EventsNavigationItem(
                selected = currentTab == Tab.EVENTS,
                onClick = { onClick(Tab.EVENTS) },
                icon = R.drawable.icon_events,
                name = R.string.tab_events,
            )

            EventsNavigationItem(
                selected = currentTab == Tab.GROUPS,
                onClick = { onClick(Tab.GROUPS) },
                icon = R.drawable.icon_groups,
                name = R.string.tab_groups,
            )

            EventsNavigationItem(
                selected = currentTab == Tab.MORE,
                onClick = { onClick(Tab.MORE) },
                icon = R.drawable.icon_more,
                name = R.string.tab_more,
            )
        }
    }
}

/*
    This feels wrong to pass around Android resource ids.
    But right now it looks like the cleanest way to do the job.
    Maybe will change that later.
 */
@Composable
fun RowScope.EventsNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    @StringRes name: Int,
) {
    AnimatedContent(
        modifier = Modifier.weight(1f), targetState = selected, transitionSpec = {
            if (selected) {
                slideInVertically { height -> height } + fadeIn() togetherWith slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() togetherWith slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }, label = "TabBar Animation"
    ) { isSelected ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = remember {
                    MutableInteractionSource()
                }, onClick = onClick, indication = null),
            contentAlignment = Alignment.Center,
        ) {
            if (isSelected) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = name), style = EventsTheme.typography.bodyText1
                    )
                    Icon(
                        painterResource(id = R.drawable.dot), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                }
            } else {
                Icon(
                    painterResource(id = icon), contentDescription = null,
                    tint = EventsTheme.colors.neutralActive,
                )
            }
        }
    }
}

enum class Tab {
    EVENTS, GROUPS, MORE, NO_TAB, NO_BAR,
}