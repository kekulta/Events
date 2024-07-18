package com.kekulta.events.presentation.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.presentation.ui.navigation.EnterCode
import com.kekulta.events.presentation.ui.navigation.EnterProfile
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.PHONE_NUMBER_LENGTH
import com.kekulta.events.presentation.ui.widgets.PhoneField
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.data.Countries
import com.kekulta.events.presentation.viewmodel.EnterPhoneScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EnterPhoneScreen(
    viewModel: EnterPhoneScreenViewModel = koinViewModel(),
) {
    val state by viewModel.observeAuthStatus().collectAsStateWithLifecycle()

    val navigator = findNavigator()

    SetTopBar {
        remember {
            EventsTopBarState(
                enabled = true, showBackButton = false, currScreenAction = null, currScreenName = ""
            )
        }
    }

    when (state) {
        is AuthStatus.CodeSent -> navigator.navTo(EnterCode())
        is AuthStatus.Unauthorized -> EnterPhoneContent(sendCode = viewModel::sendCode)
        is AuthStatus.Authorized -> navigator.setRoot(Events())
        is AuthStatus.NeedsRegistration -> navigator.navTo(EnterProfile())
    }
}

@Composable
private fun EnterPhoneContent(sendCode: (number: PhoneNumber) -> Unit) {
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
                sendCode(
                    PhoneNumber(
                        code = countryState.value.countryCode, number = numberState.text.toString()
                    )
                )
            }) {
            Text("Continue")
        }
    }
}