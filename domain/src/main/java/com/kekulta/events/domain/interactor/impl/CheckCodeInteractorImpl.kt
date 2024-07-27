package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.CheckCodeInteractor
import com.kekulta.events.domain.models.values.VerificationCode
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckCodeInteractorImpl(
    private val authRepository: AuthRepository,
) : CheckCodeInteractor {
    override suspend fun execute(verificationCode: VerificationCode) = withContext(Dispatchers.IO) {
        authRepository.checkCode(verificationCode)
    }
}