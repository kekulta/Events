package com.kekulta.events.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.ui.models.ProfileVo
import com.kekulta.events.ui.navigation.MyEvents
import com.kekulta.events.ui.navigation.Profile
import com.kekulta.events.ui.navigation.Showcase
import com.kekulta.events.ui.navigation.findNavigator
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.ProfileItem
import com.kekulta.events.ui.widgets.SettingsItem

@Composable
fun MoreScreen(
    /*
        I'm in hurry, namings and logic are mess. But I'll fix that, I promise!
     */
    navToLogin: () -> Unit,
) {
    val navigator = findNavigator()

    LazyColumn(
        modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX8),
    ) {
        item {
            ProfileItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                profileVo = ProfileVo(
                    name = "Ruslan Russkikh", phone = "+7 995 917-72-42", avatar = null
                ),
                onClick = { navigator.navTo(Profile()) })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_events),
                name = "My events",
                onClick = {
                    navigator.navTo(MyEvents())
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_search),
                name = "Showcase",
                onClick = {
                    navigator.navTo(Showcase())
                })
        }
        item {
            SettingsItem(modifier = Modifier.padding(vertical = EventsTheme.sizes.sizeX4),
                icon = painterResource(id = R.drawable.icon_pencil),
                name = "Login Flow",
                onClick = {
                    navToLogin()
                })
        }
    }
}