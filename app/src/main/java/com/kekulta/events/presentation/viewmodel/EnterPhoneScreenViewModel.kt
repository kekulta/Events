package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.SendCodeInteractor
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.PhoneNumber
import kotlinx.coroutines.flow.StateFlow

class EnterPhoneScreenViewModel(
    private val getCurrentAuthStatusInteractor: GetCurrentAuthStatusInteractor,
    private val sendCodeInteractor: SendCodeInteractor,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = getCurrentAuthStatusInteractor.execute()
    fun sendCode(number: PhoneNumber) =
        launchScope { sendCodeInteractor.execute(Identifier.Phone(number)) }
}