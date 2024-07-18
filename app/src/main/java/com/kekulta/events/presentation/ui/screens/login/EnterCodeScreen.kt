package com.kekulta.events.presentation.ui.screens.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.domain.formatters.format
import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.VerificationCode
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.EnterProfile
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.CODE_LENGTH
import com.kekulta.events.presentation.ui.widgets.CodeField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsTextButton
import com.kekulta.events.presentation.viewmodel.EnterCodeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EnterCodeScreen(
    viewModel: EnterCodeViewModel = koinViewModel()
) {
    val state by viewModel.observeAuthStatus().collectAsStateWithLifecycle()

    val navigator = findNavigator()

    SetTopBar {
        remember {
            EventsTopBarState(
                enabled = true, showBackButton = true, currScreenAction = null, currScreenName = ""
            )
        }
    }

    when (val s = state) {
        is AuthStatus.CodeSent -> EnterCodeContent(
            number = s.number.format(), checkCode = viewModel::checkCode
        )

        is AuthStatus.Unauthorized -> navigator.setRoot(EnterPhone())
        is AuthStatus.Authorized -> navigator.setRoot(Events())
        is AuthStatus.NeedsRegistration -> navigator.navTo(EnterProfile())
    }

    BackHandler {
        viewModel.logOut()
    }
}

@Composable
private fun EnterCodeContent(number: String, checkCode: (code: VerificationCode) -> Unit) {
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
            text = number,
            style = EventsTheme.typography.bodyText2
        )
        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX20))
        CodeField(state = codeState)

        Spacer(modifier = Modifier.height(EventsTheme.sizes.sizeX25))

        if (codeState.text.length == CODE_LENGTH) {
            EventsFilledButton(modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .fillMaxWidth(),
                onClick = {
                    checkCode(VerificationCode(codeState.text.toString()))
                }) {
                Text("Continue")
            }
        } else {
            EventsTextButton(modifier = Modifier
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
