package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.repository.api.AuthRepository

class RegisterInteractor(
    private val authRepository: AuthRepository,
) {
    fun execute(info: PersonalInfo): Boolean {
        return authRepository.register(info)
    }
}