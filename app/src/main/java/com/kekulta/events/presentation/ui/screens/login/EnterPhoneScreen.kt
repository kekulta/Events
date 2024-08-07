package com.kekulta.events.presentation.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.PhoneNumber
import com.kekulta.events.presentation.ui.navigation.EnterCode
import com.kekulta.events.presentation.ui.navigation.EnterProfile
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.requireNavigator
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

    val navigator = requireNavigator()

    SetTopBar {
        remember {
            EventsTopBarState(showBackButton = false)
        }
    }

    when (state) {
        is AuthStatus.NeedsVerification -> navigator.navTo(EnterCode())
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
            text = stringResource(id = R.string.enter_number),
            style = EventsTheme.typography.heading2
        )
        Text(
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .padding(top = EventsTheme.sizes.sizeX4),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.verification_send),
            style = EventsTheme.typography.bodyText2
        )

        PhoneField(
            numberState = numberState,
            countryState = countryState,
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .padding(top = EventsTheme.sizes.sizeX20),
        )

        EventsFilledButton(enabled = numberState.text.length == PHONE_NUMBER_LENGTH,
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .padding(top = EventsTheme.sizes.sizeX25)
                .fillMaxWidth(),
            onClick = {
                sendCode(
                    PhoneNumber(
                        countryCode = countryState.value.countryCode,
                        prefix = countryState.value.prefix,
                        number = numberState.text.toString()
                    )
                )
            }) {
            Text(stringResource(id = R.string.continue_button))
        }
    }
}