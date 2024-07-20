package com.kekulta.events.presentation.ui.screens.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.presentation.ui.navigation.EnterCode
import com.kekulta.events.presentation.ui.navigation.EnterPhone
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.UserCircleAddAvatar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.text.EventsInputField
import com.kekulta.events.presentation.viewmodel.EnterProfileScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EnterProfileScreen(viewModel: EnterProfileScreenViewModel = koinViewModel()) {
    val state by viewModel.observeAuthStatus().collectAsStateWithLifecycle()

    val navigator = requireNavigator()

    SetTopBar {
        remember {
            EventsTopBarState(
                enabled = true,
                showBackButton = true,
                currScreenAction = null,
                currScreenName = "Profile",
            )
        }
    }

    when (state) {
        is AuthStatus.CodeSent -> navigator.navTo(EnterCode())
        is AuthStatus.Unauthorized -> navigator.setRoot(EnterPhone())
        is AuthStatus.Authorized -> navigator.setRoot(Events())
        is AuthStatus.NeedsRegistration -> EnterProfileContent(registerProfile = viewModel::register)
    }

    BackHandler {
        viewModel.logOut()
    }
}

@Composable
private fun EnterProfileContent(registerProfile: (info: PersonalInfo) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val nameState = rememberTextFieldState()
        val surnameState = rememberTextFieldState()

        UserCircleAddAvatar(modifier = Modifier.padding(top = EventsTheme.sizes.sizeX26))
        EventsInputField(
            state = nameState,
            hint = "Name (required)",
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .padding(top = EventsTheme.sizes.sizeX12),
        )
        EventsInputField(
            state = surnameState,
            hint = "Surname (optional)",
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9)
                .padding(top = EventsTheme.sizes.sizeX4),
        )
        EventsFilledButton(enabled = nameState.text.isNotBlank(),
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .padding(top = EventsTheme.sizes.sizeX16)
                .fillMaxWidth(),
            onClick = {
                registerProfile(
                    PersonalInfo(
                        name = nameState.text.toString(),
                        surname = surnameState.text.toString()
                            .takeIf { surname -> surname.isNotBlank() },
                        avatar = Avatar(null),
                    )
                )
            }) {
            Text("Continue")
        }
    }
}