package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.SendCodeInteractor
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.repository.api.AuthRepository

class SendCodeInteractorImpl(
    private val authRepository: AuthRepository,
) : SendCodeInteractor {
    override fun execute(number: PhoneNumber): Boolean {
        return authRepository.sendCode(number)
    }
}