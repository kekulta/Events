package com.kekulta.events.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kekulta.events.ui.navigation.EnterCode
import com.kekulta.events.ui.navigation.findNavigator
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.EventsTopBarState
import com.kekulta.events.ui.widgets.PHONE_NUMBER_LENGTH
import com.kekulta.events.ui.widgets.PhoneField
import com.kekulta.events.ui.widgets.SetTopBar
import com.kekulta.events.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.ui.widgets.base.data.Countries


@Composable
fun EnterPhoneScreen() {
    val navigator = findNavigator()

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = false,
            currScreenAction = null,
            currScreenName = ""
        )
    }

    Column(
        modifier = Modifier
            .padding(top = EventsTheme.sizes.sizeX50)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val numberState = rememberTextFieldState()
        val countryState = remember { mutableStateOf(Countries.russia) }

        Text(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            text = "Enter phone number",
            style = EventsTheme.typography.heading2
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX4))
        Text(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            textAlign = TextAlign.Center,
            text = "We will send verification code\non this number",
            style = EventsTheme.typography.bodyText2
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX20))
        PhoneField(
            numberState = numberState,
            countryState = countryState,
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
        )

        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX25))

        EventsFilledButton(enabled = numberState.text.length == PHONE_NUMBER_LENGTH,
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .fillMaxWidth(),
            onClick = {
                navigator.navTo(EnterCode(phone = numberState.text.toString()))
            }) {
            Text("Continue")
        }
    }
}