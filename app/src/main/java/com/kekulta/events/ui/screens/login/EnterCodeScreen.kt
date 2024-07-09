package com.kekulta.events.ui.screens.login

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
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.CODE_LENGTH
import com.kekulta.events.ui.widgets.CodeField
import com.kekulta.events.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.ui.widgets.base.buttons.EventsTextButton


@Composable
fun EnterCodeScreen(
    /*
        Temporary solution. Actual data transfer will be trough viewmodel.
     */
    number: String,
    code: String,
    onResult: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = EventsTheme.sizes.sizeX90),
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
                    onResult()
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