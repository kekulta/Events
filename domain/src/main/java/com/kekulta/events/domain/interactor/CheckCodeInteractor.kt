package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.values.VerificationCode

interface CheckCodeInteractor {
    suspend fun execute(verificationCode: VerificationCode)
}
