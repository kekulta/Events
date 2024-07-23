package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import com.kekulta.events.domain.usecase.SendCodeUseCase
import kotlinx.coroutines.flow.StateFlow

class EnterPhoneScreenViewModel(
    private val currentAuthStatusUseCase: CurrentAuthStatusUseCase,
    private val sendCodeUseCase: SendCodeUseCase,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = currentAuthStatusUseCase.execute()
    fun sendCode(number: PhoneNumber): Boolean = sendCodeUseCase.execute(number)
}