package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.CheckCodeInteractor
import com.kekulta.events.domain.models.VerificationCode
import com.kekulta.events.domain.repository.api.AuthRepository

class CheckCodeInteractorImpl(
    private val authRepository: AuthRepository,
) : CheckCodeInteractor {
    override fun execute(verificationCode: VerificationCode): Boolean {
        return authRepository.checkCode(verificationCode)
    }
}