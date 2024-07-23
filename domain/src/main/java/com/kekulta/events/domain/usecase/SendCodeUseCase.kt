package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.repository.api.AuthRepository

class SendCodeUseCase(
    private val authRepository: AuthRepository,
) {
    fun execute(number: PhoneNumber): Boolean {
        return authRepository.sendCode(number)
    }
}