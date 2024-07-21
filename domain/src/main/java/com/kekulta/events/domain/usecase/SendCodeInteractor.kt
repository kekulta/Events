package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.repository.api.AuthRepository

class SendCodeInteractor(
    private val authRepository: AuthRepository,
) {
    fun execute(number: PhoneNumber): Boolean {
        return authRepository.sendCode(number)
    }
}