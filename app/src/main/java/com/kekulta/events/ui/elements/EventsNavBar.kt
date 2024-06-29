package com.kekulta.events.ui.elements

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
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
            EventsNavigationItem(selected = currentTab == Tab.EVENTS,
                onClick = { onClick(Tab.EVENTS) },
                selectedIcon = {
                    Icon(
                        painterResource(id = R.drawable.icon_events), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                },
                unselectedIcon = {
                    Icon(
                        painterResource(id = R.drawable.icon_plus), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                })

            EventsNavigationItem(selected = currentTab == Tab.GROUPS,
                onClick = { onClick(Tab.GROUPS) },
                selectedIcon = {
                    Icon(
                        painterResource(id = R.drawable.icon_groups), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                },
                unselectedIcon = {
                    Icon(
                        painterResource(id = R.drawable.icon_plus), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                })

            EventsNavigationItem(selected = currentTab == Tab.MORE,
                onClick = { onClick(Tab.MORE) },
                selectedIcon = {
                    Icon(
                        painterResource(id = R.drawable.icon_more), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                },
                unselectedIcon = {
                    Icon(
                        painterResource(id = R.drawable.icon_plus), contentDescription = null,
                        tint = EventsTheme.colors.neutralActive,
                    )
                })
        }
    }
}

@Composable
fun RowScope.EventsNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    selectedIcon: @Composable () -> Unit,
    unselectedIcon: @Composable () -> Unit,
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
                selectedIcon()
            } else {
                unselectedIcon()
            }
        }
    }
}

enum class Tab {
    EVENTS, GROUPS, MORE, NO_TAB, NO_BAR,
}