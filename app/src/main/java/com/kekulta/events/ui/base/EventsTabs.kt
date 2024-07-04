package com.kekulta.events.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kekulta.events.ui.elements.EventElementVo
import com.kekulta.events.ui.elements.EventsList
import com.kekulta.events.ui.showcase.mockEventsVo
import com.kekulta.events.ui.theme.EventsTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsTabs(tabs: List<EventsElementsTabVo>) {
    val pagerState = rememberPagerState {
        tabs.size
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SecondaryTabRow(
            modifier = Modifier.padding(EventsTheme.sizes.sizeX12),
            divider = {},
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    color = EventsTheme.colors.brandDefault, modifier = Modifier.tabIndicatorOffset(
                        pagerState.currentPage, matchContentSize = false
                    )
                )
            },
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent
        ) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)

                        }
                    },
                    text = {
                        /*
                            Wrong colors, wrongs fonts. Figma does not use neither of these from
                            ui kit.
                         */
                        Text(
                            text = tabItem.title,
                            style = EventsTheme.typography.subheading2,
                            color = if (index == pagerState.currentPage) EventsTheme.colors.brandDefault else EventsTheme.colors.neutralWeak
                        )
                    },
                )
            }
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            EventsList(events = tabs[index].events)
        }
    }
}

data class EventsElementsTabVo(
    val title: String, val events: List<EventElementVo>
)

fun mockTabVo(name: String): EventsElementsTabVo {
    return EventsElementsTabVo(title = name, mockEventsVo(20))
}
