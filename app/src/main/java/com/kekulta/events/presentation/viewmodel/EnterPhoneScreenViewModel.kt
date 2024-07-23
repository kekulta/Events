package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.SendCodeInteractor
import kotlinx.coroutines.flow.StateFlow

class EnterPhoneScreenViewModel(
    private val getCurrentAuthStatusInteractor: GetCurrentAuthStatusInteractor,
    private val sendCodeInteractor: SendCodeInteractor,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = getCurrentAuthStatusInteractor.execute()
    fun sendCode(number: PhoneNumber): Boolean = sendCodeInteractor.execute(number)
}