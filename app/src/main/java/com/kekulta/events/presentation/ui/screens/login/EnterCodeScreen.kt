package com.kekulta.events.presentation.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kekulta.events.presentation.ui.navigation.EnterProfile
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.CODE_LENGTH
import com.kekulta.events.presentation.ui.widgets.CodeField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsTextButton


@Composable
fun EnterCodeScreen(
    number: String,
) {
    val navigator = findNavigator()

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = true,
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
        val codeState = rememberTextFieldState()

        Text(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            text = "Enter the code",
            style = EventsTheme.typography.heading2
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX4))
        Text(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            textAlign = TextAlign.Center,
            text = "We sent in on the number",
            style = EventsTheme.typography.bodyText2
        )
        Text(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            textAlign = TextAlign.Center,
            text = formatNumber(number),
            style = EventsTheme.typography.bodyText2
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX20))
        CodeField(state = codeState)

        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX25))

        if (codeState.text.length == CODE_LENGTH) {
            EventsFilledButton(
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX5)
                    .fillMaxWidth(),
                onClick = {
                    navigator.navTo(EnterProfile())
                }) {
                Text("Continue")
            }
        } else {
            EventsTextButton(
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX5)
                    .fillMaxWidth(),
                onClick = {
                    /* TODO */
                }) {
                Text("Resend the code")
            }
        }
    }
}

private fun formatNumber(number: String): String {
    val builder = StringBuilder(number)
    builder.insert(2, " ")
    builder.insert(6, " ")
    builder.insert(10, "-")
    builder.insert(13, "-")

    return builder.toString()
}