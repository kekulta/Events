package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.VerificationCode

interface CheckCodeInteractor {
    fun execute(verificationCode: VerificationCode): Boolean
}
