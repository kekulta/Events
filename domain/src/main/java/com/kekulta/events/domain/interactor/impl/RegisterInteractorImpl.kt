package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.RegisterInteractor
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.repository.api.AuthRepository

class RegisterInteractorImpl(
    private val authRepository: AuthRepository,
): RegisterInteractor {
    override fun execute(info: PersonalInfo): Boolean {
        return authRepository.register(info)
    }
}