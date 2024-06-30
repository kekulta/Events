package com.kekulta.events.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.MyEvents
import com.kekulta.events.Profile
import com.kekulta.events.R
import com.kekulta.events.Showcase
import com.kekulta.events.ui.base.ProfileItem
import com.kekulta.events.ui.base.SettingsItem
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun MoreScreen(
    navigate: Navigate,
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX8),
    ) {
        item {
            ProfileItem(
                modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                profileVo = ProfileVO(
                    name = "Ruslan Russkikh",
                    phone = "+7 995 917-72-42",
                    avatar = null
                ), onClick = { navigate(Profile) })
        }
        item {
            SettingsItem(
                modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_events),
                name = "My events",
                onClick = {
                    navigate(MyEvents)
                })
        }
        item {
            SettingsItem(
                modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_search),
                name = "Showcase",
                onClick = {
                    navigate(Showcase)
                })
        }
    }
}