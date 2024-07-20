package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.repository.api.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository,
) {
    fun execute(info: PersonalInfo): Boolean {
        return authRepository.register(info)
    }
}