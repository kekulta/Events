package com.kekulta.events.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.navigation.Events
import com.kekulta.events.ui.navigation.findNavigator
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.EventsTopBarState
import com.kekulta.events.ui.widgets.SetTopBar
import com.kekulta.events.ui.widgets.UserCircleAddAvatar
import com.kekulta.events.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.ui.widgets.base.text.EventsInputField

@Composable
fun EnterProfileScreen() {
    val navigator = findNavigator()

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = true,
            currScreenAction = null,
            currScreenName = "Profile",
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textState = rememberTextFieldState()

        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX26))
        UserCircleAddAvatar()
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX12))
        EventsInputField(
            state = textState,
            hint = "Name (required)",
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX4))
        EventsInputField(
            hint = "Surname (optional)",
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX16))
        EventsFilledButton(
            enabled = textState.text.isNotBlank(),
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .fillMaxWidth(),
            onClick = {
                navigator.setRoot(Events())
            }) {
            Text("Continue")
        }
    }
}
