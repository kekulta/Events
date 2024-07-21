package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.VerificationCode
import com.kekulta.events.domain.repository.api.AuthRepository

class CheckCodeInteractor(
    private val authRepository: AuthRepository,
) {
    fun execute(verificationCode: VerificationCode): Boolean {
        return authRepository.checkCode(verificationCode)
    }
}