package com.kekulta.events.presentation.ui.screens.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.R
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.VerificationCode
import com.kekulta.events.presentation.formatters.format
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.EnterProfile
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.CODE_LENGTH
import com.kekulta.events.presentation.ui.widgets.CodeField
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsTextButton
import com.kekulta.events.presentation.ui.widgets.base.modifiers.rememberShakeController
import com.kekulta.events.presentation.ui.widgets.base.modifiers.shake
import com.kekulta.events.presentation.viewmodel.EnterCodeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EnterCodeScreen(
    viewModel: EnterCodeScreenViewModel = koinViewModel()
) {
    val state by viewModel.observeAuthStatus().collectAsStateWithLifecycle()

    val navigator = requireNavigator()

    SetTopBar {
        remember {
            EventsTopBarState()
        }
    }

    when (val s = state) {
        is AuthStatus.NeedsVerification -> EnterCodeContent(
            number = s.identifier.number?.format() ?: s.identifier.address?.address
            ?: "Oops!", checkCode = viewModel::checkCode
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
        val shakeController = rememberShakeController()

        Text(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            text = stringResource(id = R.string.enter_code),
            style = EventsTheme.typography.heading2
        )
        Text(
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .padding(top = EventsTheme.sizes.sizeX4),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.code_sent),
            style = EventsTheme.typography.bodyText2
        )
        Text(
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .padding(bottom = EventsTheme.sizes.sizeX20),
            textAlign = TextAlign.Center,
            text = number,
            style = EventsTheme.typography.bodyText2
        )
        CodeField(
            state = codeState,
            modifier = Modifier.padding(bottom = EventsTheme.sizes.sizeX25)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shake(shakeController)
        ) {
            if (codeState.text.length == CODE_LENGTH) {
                EventsFilledButton(modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX5)
                    .fillMaxWidth(), onClick = {
                    checkCode(VerificationCode(codeState.text.toString()))
                }) {
                    Text(stringResource(id = R.string.continue_button))
                }
            } else {
                EventsTextButton(modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX5)
                    .fillMaxWidth(), onClick = {
                    /* TODO */
                }) {
                    Text(stringResource(id = R.string.resend_code))
                }
            }
        }
    }
}
