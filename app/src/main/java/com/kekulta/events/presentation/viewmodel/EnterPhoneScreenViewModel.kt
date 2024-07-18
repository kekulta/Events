package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class CurrentAuthStatusUseCase(
    private val authRepository: AuthRepository,
) {
    fun execute(): StateFlow<AuthStatus> {
        return authRepository.observeAuthStatus()
    }
}

class SendCodeUseCase(
    private val authRepository: AuthRepository,
) {
    fun execute(number: PhoneNumber): Boolean {
        return authRepository.sendCode(number)
    }
}

class EnterPhoneScreenViewModel(
    private val currentAuthStatusUseCase: CurrentAuthStatusUseCase,
    private val sendCodeUseCase: SendCodeUseCase,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = currentAuthStatusUseCase.execute()
    fun sendCode(number: PhoneNumber): Boolean = sendCodeUseCase.execute(number)
}